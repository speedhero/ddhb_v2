package com.huatek.hbwebsite.search.service;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.common.entity.Area;
import com.huatek.hbwebsite.common.entity.AreaSecond;
import com.huatek.hbwebsite.common.entity.CBDWebsite;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.common.entity.Decorations;
import com.huatek.hbwebsite.common.entity.Furniture;
import com.huatek.hbwebsite.common.entity.Hospital;
import com.huatek.hbwebsite.common.entity.Orientations;
import com.huatek.hbwebsite.common.entity.RentType;
import com.huatek.hbwebsite.common.entity.School;
import com.huatek.hbwebsite.common.entity.Station;
import com.huatek.hbwebsite.common.entity.StudyZoneType;
import com.huatek.hbwebsite.common.entity.SubwayLine;
import com.huatek.hbwebsite.common.entity.SubwayStation;
import com.huatek.hbwebsite.common.entity.Tag;
import com.huatek.hbwebsite.common.entity.Type;
import com.huatek.hbwebsite.common.entity.Usage;
import com.huatek.hbwebsite.house.entity.CityForNewHouse;
import com.huatek.hbwebsite.house.entity.CountyForNewHouse;
import com.huatek.hbwebsite.search.entity.SearchElevator;
import com.huatek.hbwebsite.search.entity.SearchField;
import com.huatek.hbwebsite.search.entity.SearchFiveYear;
import com.huatek.hbwebsite.search.entity.SearchItem;
import com.huatek.hbwebsite.search.service.SearchService;
import com.huatek.hbwebsite.service.BaseServiceToImpl;
import com.huatek.hbwebsite.util.GsonUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class SearchServiceImpl extends BaseServiceToImpl implements SearchService {
	private static final String EMPTY_ARRAY = "[]";
	private static final String GET_FIRST_LEVEL_SEARCH_ITEM_SQL = " from SearchItem si where si.modulename = ? and si.parent = 0 and si.deleteFlag = 0 order by si.sortindex desc";
	private static final String GET_SUBLEVEL_SEARCH_ITEM_SQL = " from SearchItem si where si.parent = ? and si.deleteFlag = 0  order by si.sortindex desc";
	private static final String GET_SEARCH_FIELD = " from SearchField sf where sf.searchItem = ? and sf.deleteFlag = 0";
	private static final String GET_FIRST_LEVEL_ITEM = " from SearchItem si where si.modulename = ? and si.parent = 0 and si.deleteFlag = 0";
	private static final String GET_PRIVATE_SUB_LEVEL_ITEM = " from SearchItem si where si.parent = ? and si.isPublic = 0 and si.deleteFlag = 0";
	private static final String GET_PUBLIC_SUB_LEVEL_ITEM = " from SearchItem si where si.parent = ? and si.isPublic = 1 and si.deleteFlag = 0";

	public List<SearchItem> loadFirstLevelSearchItem(String moduleName) {
		return getHibernateTemplate().find(
						" from SearchItem si where si.modulename = ? and si.parent = 0 and si.deleteFlag = 0 order by si.sortindex desc",
						moduleName);
	}

	/**
	 * 查询二手房搜索条件
	 */
	public String loadSearchMenuByModuleName(String moduleName) {
		List firstLevelSearchItem = getHibernateTemplate().find(
				" from SearchItem si where si.modulename = ? and si.parent = 0 and si.deleteFlag = 0", moduleName);

		for (int ii = 0; ii < firstLevelSearchItem.size(); ++ii) {
			List privateSubSearchItemList = this.getHibernateTemplate().find(
					" from SearchItem si where si.parent = ? and si.isPublic = 0 and si.deleteFlag = 0",
					((SearchItem) firstLevelSearchItem.get(ii)).getId());
			Iterator sim = privateSubSearchItemList.iterator();

			List searchFields1;
			while (sim.hasNext()) {
				SearchItem pubItem = (SearchItem) sim.next();
			
				List searchFields;
				if (pubItem.getIsFromTable() != 1) {
					searchFields = this.loadSearchFields(pubItem.getId().longValue());
					pubItem.setSearchFileds(searchFields);
				} else {
					searchFields = null;
					if (pubItem.getEntityName().trim().equals("Community")) {
						searchFields = this.loadQuery(pubItem.getEntityName(), (String) null, " entity.inital");
					} else if (!moduleName.equals("5") && !moduleName.equals("8")) {
						if (pubItem.getEntityName().equals("Area")) {
							searchFields = this.loadQuery(pubItem.getEntityName(), (String) null, " sortFlag");
						} else {
							searchFields = this.loadQuery(pubItem.getEntityName(), (String) null, (String) null);
						}
					} else {
						searchFields = this.loadQuery(pubItem.getEntityName(), " entity.isSearchIndividual = 1 ",
								(String) null);
					}

					if (searchFields.size() > 0) {
						searchFields1 = this.getSubItemsByList(searchFields, pubItem, moduleName);
						pubItem.setSearchFileds(searchFields1);
					}
				}
			}

			((SearchItem) firstLevelSearchItem.get(ii)).setSubItems(privateSubSearchItemList);
			if (ii == 0) {
				List var11 = this.hibernateTemplate.find(
						" from SearchItem si where si.parent = ? and si.isPublic = 1 and si.deleteFlag = 0",
						((SearchItem) firstLevelSearchItem.get(ii)).getId());
				((SearchItem) firstLevelSearchItem.get(ii)).getSubItems().addAll(var11);
				Iterator var13 = var11.iterator();

				while (var13.hasNext()) {
					SearchItem var12 = (SearchItem) var13.next();
					if (var12.getIsFromTable() == 1) {
						searchFields1 = null;
						if ((moduleName.equals("5") || moduleName.equals("8")) && var12.getEntityName().equals("Tag")) {
							searchFields1 = this.loadQuery(var12.getEntityName(), " entity.isSearchIndividual = 1 ", (String) null);
						} else {
							searchFields1 = this.loadQuery(var12.getEntityName(), (String) null, (String) null);
						}

						if (searchFields1.size() > 0) {
							List fields = this.getSubItemsByList(searchFields1, var12, moduleName);
							var12.setSearchFileds(fields);
						}
					} else {
						searchFields1 = this.loadSearchFields(var12.getId().longValue());
						var12.setSearchFileds(searchFields1);
					}
				}
			}
		}

		String var10 = null;
		if (firstLevelSearchItem.size() == 0) {
			var10 = "[]";
		} else {
			var10 = GsonUtil.getGsonInstanceWithExpose().toJson(firstLevelSearchItem);
		}

		return var10;
	}

	public List<SearchItem> loadSublevelSearchItem(long parentId) {
		return super.getHibernateTemplate().find(
				" from SearchItem si where si.parent = ? and si.deleteFlag = 0  order by si.sortindex desc",
				Long.valueOf(parentId));
	}

	public List<SearchField> loadSearchFields(long itemId) {
		return super.getHibernateTemplate().find(" from SearchField sf where sf.searchItem = ? and sf.deleteFlag = 0",
				Long.valueOf(itemId));
	}

	public List<BaseEntity> loadQuery(String entityName, String queryCondition, String orderBy) {
		StringBuilder sb = new StringBuilder();
		sb.append(" from " + entityName + " entity where entity.deleteFlag = 0");
		if (!StringUtils.isBlank(queryCondition)) {
			sb.append(" and ");
			sb.append(queryCondition);
		}

		if (!StringUtils.isBlank(orderBy)) {
			sb.append(" order by ");
			sb.append(orderBy);
		}

		return this.getHibernateTemplate().find(sb.toString());
	}

	
	
	public String loadInitSearchMenu(String modulename) {
		List searchItems = this.loadFirstLevelSearchItem(modulename);
		String returnSring = null;
		if (searchItems.size() == 0) {
			returnSring = "[]";
		} else {
			this.getSubItem(searchItems, modulename);
			returnSring = GsonUtil.getGsonInstanceWithExpose().toJson(searchItems);
		}

		return returnSring;
	}

	private void getSubItem(List<SearchItem> searchItemsParam, String moduleName) {
		List itemsList = searchItemsParam;
		if (searchItemsParam.size() > 0) {
			if (((SearchItem) searchItemsParam.get(0)).getIsParent() == 1) {
				List var7 = this.loadSublevelSearchItem(((SearchItem) searchItemsParam.get(0)).getId().longValue());
				this.getSubItem(var7, moduleName);
				((SearchItem) searchItemsParam.get(0)).setSubItems(var7);
			} else {
				for (int k = 0; k < itemsList.size(); ++k) {
					List searchFields;
					if (((SearchItem) itemsList.get(k)).getIsFromTable() == 1) {
						searchFields = this
								.loadQuery(((SearchItem) itemsList.get(k)).getEntityName(), (String) null, (String) null);
						if (searchFields.size() > 0) {
							List fields = this.getSubItemsByList(searchFields, (SearchItem) itemsList.get(k), moduleName);
							((SearchItem) itemsList.get(k)).setSearchFileds(fields);
						}
					} else {
						searchFields = this.loadSearchFields(((SearchItem) itemsList.get(k)).getId().longValue());
						((SearchItem) itemsList.get(k)).setSearchFileds(searchFields);
					}
				}

			}
		}
	}

	private List<SearchField> getSubItemsByList(List<BaseEntity> subItems, SearchItem searchItem, String modulename) {
		ArrayList fields = new ArrayList();
		SearchField searchField = null;
		CityForNewHouse cityForNewHouse;
		int i;
		List subvalue;
		CountyForNewHouse subFieldMap;
		SearchField subField;
		BaseEntity be;
		Iterator be1;
		LinkedHashMap var23;
		if (subItems.get(0) instanceof Area) {
			cityForNewHouse = null;

			for (i = 0; i < subItems.size(); ++i) {
				//这里应该为AreaSecond
				Area var46 = (Area) subItems.get(i);
				searchField = new SearchField();
				searchField.setFieldName(var46.getCountyName());
				searchField.setFieldValue(String.valueOf(var46.getErpId()));
				//获取对应商圈  searchItem.getSubEntityName() 值为CDBWebsite
				subvalue = this.loadQuery(searchItem.getSubEntityName(), " entity.area.erpId = \'" + var46.getErpId() + "\'",
						"entity.initial");
				var23 = new LinkedHashMap();
				if (subvalue.size() > 0) {
					subFieldMap = null;
					subField = null;

					List var43;
					for (be1 = subvalue.iterator(); be1.hasNext(); var43.add(subField)) {
						be = (BaseEntity) be1.next();
						CBDWebsite var36 = (CBDWebsite) be;
						if (!var23.containsKey(String.valueOf(var36.getInitial()))) {
							ArrayList var42 = new ArrayList();
							var23.put(String.valueOf(var36.getInitial()), var42);
						}

						var43 = (List) var23.get(String.valueOf(var36.getInitial()));
						subField = new SearchField(var36.getName(), String.valueOf(var36.getWebsiteId()));
						if (searchItem.getModulename().equals("4")) {
							subField.setFieldColumnName("ddhb_one_cbd.parentCBD.websiteId");
						} else {
							subField.setFieldColumnName("ddhb_one_community.cbd.parentCBD.websiteId");
						}
					}
				}

				searchField.setSubFiledsMap(var23);
				fields.add(searchField);
			}

			return fields;
		} else if (subItems.get(0) instanceof Type) {
			cityForNewHouse = null;

			for (i = 0; i < subItems.size(); ++i) {
				Type var45 = (Type) subItems.get(i);
				searchField = new SearchField();
				searchField.setFieldName(var45.getHouseTypeName());
				searchField.setFieldValue(var45.getErpId());
				fields.add(searchField);
			}

			return fields;
		} else if (subItems.get(0) instanceof Orientations) {
			cityForNewHouse = null;

			for (i = 0; i < subItems.size(); ++i) {
				Orientations var44 = (Orientations) subItems.get(i);
				searchField = new SearchField();
				searchField.setFieldName(var44.getOrientationName());
				searchField.setFieldValue(String.valueOf(var44.getId()));
				fields.add(searchField);
			}

			return fields;
		} else if (subItems.get(0) instanceof Decorations) {
			cityForNewHouse = null;

			for (i = 0; i < subItems.size(); ++i) {
				Decorations var41 = (Decorations) subItems.get(i);
				searchField = new SearchField();
				searchField.setFieldName(var41.getDecorationName());
				searchField.setFieldValue(var41.getErpId());
				fields.add(searchField);
			}

			return fields;
		} else if (subItems.get(0) instanceof SubwayLine) {
			cityForNewHouse = null;

			for (i = 0; i < subItems.size(); ++i) {
				SubwayLine var39 = (SubwayLine) subItems.get(i);
				searchField = new SearchField();
				searchField.setFieldName(var39.getSubwayName());
				searchField.setFieldValue(var39.getErpId());
				subvalue = this.loadQuery(searchItem.getSubEntityName(), " entity.subwayLine.erpId = \'" + var39.getErpId()
						+ "\'", (String) null);
				var23 = new LinkedHashMap();
				ArrayList var34 = new ArrayList();
				if (subvalue.size() > 0) {
					subField = null;
					be = null;
					Iterator list = subvalue.iterator();

					while (list.hasNext()) {
						BaseEntity var40 = (BaseEntity) list.next();
						SubwayStation var37 = (SubwayStation) var40;
						SearchField var38 = new SearchField(var37.getStationName(), var37.getErpId());
						var38.setFieldColumnName("ddhb_one_subwaystation.erpId");
						var34.add(var38);
					}
				}

				if (var34.size() > 0) {
					var23.put("_", var34);
				}

				searchField.setSubFiledsMap(var23);
				fields.add(searchField);
			}

			return fields;
		} else {
			ArrayList countyList;
			LinkedHashMap var25;
			if (subItems.get(0) instanceof StudyZoneType) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					StudyZoneType var35 = (StudyZoneType) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var35.getStutName());
					searchField.setFieldValue(var35.getErpId());
					subvalue = this.loadQuery(searchItem.getSubEntityName(),
							" entity.studyZoneType.erpId = \'" + var35.getErpId() + "\'", (String) null);
					countyList = new ArrayList();
					if (subvalue.size() > 0) {
						subFieldMap = null;
						subField = null;
						be1 = subvalue.iterator();

						while (be1.hasNext()) {
							be = (BaseEntity) be1.next();
							School var30 = (School) be;
							subField = new SearchField(var30.getStuName(), var30.getErpId());
							if(var30.getRegion() == null)
								continue;
							subField.setSchoolCountryId(var30.getRegion().getErpId());
							subField.setFieldColumnName("ddhb_one_school.erpId");
							countyList.add(subField);
						}
					}

					var25 = new LinkedHashMap();
					if (countyList.size() > 0) {
						var25.put("_", countyList);
					}

					searchField.setSubFiledsMap(var25);
					fields.add(searchField);
				}

				return fields;
			} else if (subItems.get(0) instanceof Tag) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					Tag var32 = (Tag) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var32.getTagName());
					searchField.setFieldValue(var32.getErpId());
					searchField.setBgColor(var32.getTagColor());
					fields.add(searchField);
				}

				return fields;
			} else if (subItems.get(0) instanceof Station) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					Station var28 = (Station) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var28.getStationName());
					searchField.setFieldValue(var28.getErpId());
					fields.add(searchField);
				}

				return fields;
			} else if (subItems.get(0) instanceof Hospital) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					Hospital var26 = (Hospital) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var26.getHospitalName());
					searchField.setFieldValue(var26.getErpId());
					fields.add(searchField);
				}

				return fields;
			} else if (subItems.get(0) instanceof Usage) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					Usage var24 = (Usage) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var24.getUsageName());
					searchField.setFieldValue(var24.getErpId());
					fields.add(searchField);
				}

				return fields;
			} else if (subItems.get(0) instanceof SearchElevator) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					SearchElevator var22 = (SearchElevator) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var22.getTitle());
					searchField.setFieldValue(String.valueOf(var22.getValue()));
					fields.add(searchField);
				}

				return fields;
			} else if (subItems.get(0) instanceof SearchFiveYear) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					SearchFiveYear var20 = (SearchFiveYear) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var20.getTitle());
					searchField.setFieldValue(String.valueOf(var20.getValue()));
					fields.add(searchField);
				}

				return fields;
			} else if (subItems.get(0) instanceof Community) {
				cityForNewHouse = null;
				LinkedHashMap var19 = new LinkedHashMap();
				subvalue = null;

				for (int var21 = 0; var21 < subItems.size(); ++var21) {
					Community var17 = (Community) subItems.get(var21);
					SearchField var18;
					if (var19.containsKey(var17.getInital())) {
						var18 = new SearchField(var17.getCommunityName(), var17.getErpId());
						var18.setFieldColumnName("ddhb_one_erpId");
						int var27 = ((Integer) var19.get(var17.getInital())).intValue();
						searchField = (SearchField) fields.get(var27);
						Map var29 = searchField.getSubFiledsMap();
						List var33 = (List) var29.get("_");
						var33.add(var18);
					} else {
						searchField = new SearchField();
						searchField.setFieldName(var17.getInital());
						searchField.setFieldValue(var17.getInital());
						fields.add(searchField);
						var19.put(var17.getInital(), Integer.valueOf(fields.indexOf(searchField)));
						var25 = new LinkedHashMap();
						var18 = new SearchField(var17.getCommunityName(), var17.getCommunityName());
						var18.setFieldColumnName("ddhb_one_erpId");
						ArrayList var31 = new ArrayList();
						var31.add(var18);
						var25.put("_", var31);
						searchField.setSubFiledsMap(var25);
					}
				}

				return fields;
			} else if (subItems.get(0) instanceof Furniture) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					Furniture var16 = (Furniture) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var16.getFurName());
					searchField.setFieldValue(var16.getErpId());
					fields.add(searchField);
				}

				return fields;
			} else if (subItems.get(0) instanceof RentType) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					RentType var15 = (RentType) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var15.getRentTypeName());
					searchField.setFieldValue(String.valueOf(var15.getRentValue()));
					fields.add(searchField);
				}

				return fields;
			} else if (!(subItems.get(0) instanceof CityForNewHouse)) {
				return null;
			} else {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					cityForNewHouse = (CityForNewHouse) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(cityForNewHouse.getCityName());
					searchField.setFieldValue(String.valueOf(cityForNewHouse.getId()));
					subvalue = this.loadQuery(searchItem.getSubEntityName(),
							" entity.cityForNewHouse.id = " + cityForNewHouse.getId(), (String) null);
					countyList = new ArrayList();
					if (subvalue.size() > 0) {
						subFieldMap = null;
						subField = null;
						be1 = subvalue.iterator();

						while (be1.hasNext()) {
							be = (BaseEntity) be1.next();
							subFieldMap = (CountyForNewHouse) be;
							subField = new SearchField(subFieldMap.getCountyName(), String.valueOf(subFieldMap.getId()));
							subField.setFieldColumnName("ddhb_one_area.id");
							countyList.add(subField);
						}
					}

					var25 = new LinkedHashMap();
					if (countyList.size() > 0) {
						var25.put("_", countyList);
					}

					searchField.setSubFiledsMap(var25);
					fields.add(searchField);
				}

				return fields;
			}
		}
	}
	
	/**
	 *  用于租赁SEO分类信息
	 * Modify by 何剑波 20150407
	 */
	@Override
	public String loadSearchMenuByModuleNameSecond(String moduleName) {
		List firstLevelSearchItem = this.getHibernateTemplate().find(
				" from SearchItem si where si.modulename = ? and si.parent = 0 and si.deleteFlag = 0", moduleName);

		for (int returnSring = 0; returnSring < firstLevelSearchItem.size(); ++returnSring) {
			new ArrayList();
			List privateSubSearchItemList = this.getHibernateTemplate().find(
					" from SearchItem si where si.parent = ? and si.isPublic = 0 and si.deleteFlag = 0",
					((SearchItem) firstLevelSearchItem.get(returnSring)).getId());
			Iterator sim = privateSubSearchItemList.iterator();

			List searchFields1;
			while (sim.hasNext()) {
				SearchItem privItem = (SearchItem) sim.next();
				List searchFields;
				if (privItem.getIsFromTable() != 1) {
					searchFields = this.loadSearchFields(privItem.getId().longValue());
					privItem.setSearchFileds(searchFields);
				} else {
					searchFields = null;
					if (privItem.getEntityName().trim().equals("Community")) {
						searchFields = this.loadQuery(privItem.getEntityName(), (String) null, " entity.inital");
					} else if (!moduleName.equals("5") && !moduleName.equals("8")) {
						if (privItem.getEntityName().equals("Area")) {
							//searchFields = this.loadQuery(privItem.getEntityName(), (String) null, " sortFlag");
							searchFields = this.loadQuery("AreaSecond", (String) null, " sortFlag");
						} else {
							searchFields = this.loadQuery(privItem.getEntityName(), (String) null, (String) null);
						}
					} else {
						searchFields = this.loadQuery(privItem.getEntityName(), " entity.isSearchIndividual = 1 ",
								(String) null);
					}

					if (searchFields.size() > 0) {
						searchFields1 = this.getSubItemsByListSecond(searchFields, privItem, moduleName);
						privItem.setSearchFileds(searchFields1);
					}
				}
			}

			((SearchItem) firstLevelSearchItem.get(returnSring)).setSubItems(privateSubSearchItemList);
			if (returnSring == 0) {
				List var11 = this.hibernateTemplate.find(
						" from SearchItem si where si.parent = ? and si.isPublic = 1 and si.deleteFlag = 0",
						((SearchItem) firstLevelSearchItem.get(returnSring)).getId());
				((SearchItem) firstLevelSearchItem.get(returnSring)).getSubItems().addAll(var11);
				Iterator var13 = var11.iterator();

				while (var13.hasNext()) {
					SearchItem var12 = (SearchItem) var13.next();
					if (var12.getIsFromTable() == 1) {
						searchFields1 = null;
						if ((moduleName.equals("5") || moduleName.equals("8")) && var12.getEntityName().equals("Tag")) {
							searchFields1 = this.loadQuery(var12.getEntityName(), " entity.isSearchIndividual = 1 ", (String) null);
						} else {
							searchFields1 = this.loadQuery(var12.getEntityName(), (String) null, (String) null);
						}

						if (searchFields1.size() > 0) {
							List fields = this.getSubItemsByListSecond(searchFields1, var12, moduleName);
							var12.setSearchFileds(fields);
						}
					} else {
						searchFields1 = this.loadSearchFields(var12.getId().longValue());
						var12.setSearchFileds(searchFields1);
					}
				}
			}
		}

		String var10 = null;
		if (firstLevelSearchItem.size() == 0) {
			var10 = "[]";
		} else {
			var10 = GsonUtil.getGsonInstanceWithExpose().toJson(firstLevelSearchItem);
		}

		return var10;
	}
	
	/**
	 * 用于租赁SEO分类信息
	 * Modify by 何剑波 20150407
	 * @param subItems
	 * @param searchItem
	 * @param modulename
	 * @return
	 */
	private List<SearchField> getSubItemsByListSecond(List<BaseEntity> subItems, SearchItem searchItem, String modulename) {
		ArrayList fields = new ArrayList();
		SearchField searchField = null;
		CityForNewHouse cityForNewHouse;
		int i;
		List subvalue;
		CountyForNewHouse subFieldMap;
		SearchField subField;
		BaseEntity be;
		Iterator be1;
		LinkedHashMap var23;
		if (subItems.get(0) instanceof AreaSecond) {
			cityForNewHouse = null;

			for (i = 0; i < subItems.size(); ++i) {
				//修改start HeJB
				//这里应该为AreaSecond
				AreaSecond var46 = (AreaSecond) subItems.get(i);
				searchField = new SearchField();
				searchField.setFieldName(var46.getCountyName());
				searchField.setFieldValue(String.valueOf(var46.getErpId()));
				//获取对应商圈  searchItem.getSubEntityName() 值为CBDWebsite
				subvalue = new ArrayList();
				String[] ids = var46.getCommonIds().split(",");
				for(String s : ids){
					List<BaseEntity> list =	this.loadQuery(searchItem.getSubEntityName(), " entity.websiteId = \'" + Integer.parseInt(s) + "\'",
						"entity.initial");
					
					for( BaseEntity b : list){
						subvalue.add(b);
					}
				}
				//END 
				var23 = new LinkedHashMap();
				if (subvalue.size() > 0) {
					subFieldMap = null;
					subField = null;

					List var43;
					for (be1 = subvalue.iterator(); be1.hasNext(); var43.add(subField)) {
						be = (BaseEntity) be1.next();
						CBDWebsite var36 = (CBDWebsite) be;
						if (!var23.containsKey(String.valueOf(var36.getInitial()))) {
							ArrayList var42 = new ArrayList();
							var23.put(String.valueOf(var36.getInitial()), var42);
						}

						var43 = (List) var23.get(String.valueOf(var36.getInitial()));
						subField = new SearchField(var36.getName(), String.valueOf(var36.getWebsiteId()));
						if (searchItem.getModulename().equals("4")) {
							subField.setFieldColumnName("ddhb_one_cbd.parentCBD.websiteId");
						} else {
							subField.setFieldColumnName("ddhb_one_community.cbd.parentCBD.websiteId");
						}
					}
				}

				searchField.setSubFiledsMap(var23);
				fields.add(searchField);
			}

			return fields;
		} else if (subItems.get(0) instanceof Type) {
			cityForNewHouse = null;

			for (i = 0; i < subItems.size(); ++i) {
				Type var45 = (Type) subItems.get(i);
				searchField = new SearchField();
				searchField.setFieldName(var45.getHouseTypeName());
				searchField.setFieldValue(var45.getErpId());
				fields.add(searchField);
			}

			return fields;
		} else if (subItems.get(0) instanceof Orientations) {
			cityForNewHouse = null;

			for (i = 0; i < subItems.size(); ++i) {
				Orientations var44 = (Orientations) subItems.get(i);
				searchField = new SearchField();
				searchField.setFieldName(var44.getOrientationName());
				searchField.setFieldValue(String.valueOf(var44.getId()));
				fields.add(searchField);
			}

			return fields;
		} else if (subItems.get(0) instanceof Decorations) {
			cityForNewHouse = null;

			for (i = 0; i < subItems.size(); ++i) {
				Decorations var41 = (Decorations) subItems.get(i);
				searchField = new SearchField();
				searchField.setFieldName(var41.getDecorationName());
				searchField.setFieldValue(var41.getErpId());
				fields.add(searchField);
			}

			return fields;
		} else if (subItems.get(0) instanceof SubwayLine) {
			cityForNewHouse = null;

			for (i = 0; i < subItems.size(); ++i) {
				SubwayLine var39 = (SubwayLine) subItems.get(i);
				searchField = new SearchField();
				searchField.setFieldName(var39.getSubwayName());
				searchField.setFieldValue(var39.getErpId());
				subvalue = this.loadQuery(searchItem.getSubEntityName(), " entity.subwayLine.erpId = \'" + var39.getErpId()
						+ "\'", (String) null);
				var23 = new LinkedHashMap();
				ArrayList var34 = new ArrayList();
				if (subvalue.size() > 0) {
					subField = null;
					be = null;
					Iterator list = subvalue.iterator();

					while (list.hasNext()) {
						BaseEntity var40 = (BaseEntity) list.next();
						SubwayStation var37 = (SubwayStation) var40;
						SearchField var38 = new SearchField(var37.getStationName(), var37.getErpId());
						var38.setFieldColumnName("ddhb_one_subwaystation.erpId");
						var34.add(var38);
					}
				}

				if (var34.size() > 0) {
					var23.put("_", var34);
				}

				searchField.setSubFiledsMap(var23);
				fields.add(searchField);
			}

			return fields;
		} else {
			ArrayList countyList;
			LinkedHashMap var25;
			if (subItems.get(0) instanceof StudyZoneType) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					StudyZoneType var35 = (StudyZoneType) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var35.getStutName());
					searchField.setFieldValue(var35.getErpId());
					subvalue = this.loadQuery(searchItem.getSubEntityName(),
							" entity.studyZoneType.erpId = \'" + var35.getErpId() + "\'", (String) null);
					countyList = new ArrayList();
					if (subvalue.size() > 0) {
						subFieldMap = null;
						subField = null;
						be1 = subvalue.iterator();

						while (be1.hasNext()) {
							be = (BaseEntity) be1.next();
							School var30 = (School) be;
							subField = new SearchField(var30.getStuName(), var30.getErpId());
							subField.setFieldColumnName("ddhb_one_school.erpId");
							countyList.add(subField);
						}
					}

					var25 = new LinkedHashMap();
					if (countyList.size() > 0) {
						var25.put("_", countyList);
					}

					searchField.setSubFiledsMap(var25);
					fields.add(searchField);
				}

				return fields;
			} else if (subItems.get(0) instanceof Tag) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					Tag var32 = (Tag) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var32.getTagName());
					searchField.setFieldValue(var32.getErpId());
					searchField.setBgColor(var32.getTagColor());
					fields.add(searchField);
				}

				return fields;
			} else if (subItems.get(0) instanceof Station) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					Station var28 = (Station) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var28.getStationName());
					searchField.setFieldValue(var28.getErpId());
					fields.add(searchField);
				}

				return fields;
			} else if (subItems.get(0) instanceof Hospital) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					Hospital var26 = (Hospital) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var26.getHospitalName());
					searchField.setFieldValue(var26.getErpId());
					fields.add(searchField);
				}

				return fields;
			} else if (subItems.get(0) instanceof Usage) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					Usage var24 = (Usage) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var24.getUsageName());
					searchField.setFieldValue(var24.getErpId());
					fields.add(searchField);
				}

				return fields;
			} else if (subItems.get(0) instanceof SearchElevator) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					SearchElevator var22 = (SearchElevator) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var22.getTitle());
					searchField.setFieldValue(String.valueOf(var22.getValue()));
					fields.add(searchField);
				}

				return fields;
			} else if (subItems.get(0) instanceof SearchFiveYear) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					SearchFiveYear var20 = (SearchFiveYear) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var20.getTitle());
					searchField.setFieldValue(String.valueOf(var20.getValue()));
					fields.add(searchField);
				}

				return fields;
			} else if (subItems.get(0) instanceof Community) {
				cityForNewHouse = null;
				LinkedHashMap var19 = new LinkedHashMap();
				subvalue = null;

				for (int var21 = 0; var21 < subItems.size(); ++var21) {
					Community var17 = (Community) subItems.get(var21);
					SearchField var18;
					if (var19.containsKey(var17.getInital())) {
						var18 = new SearchField(var17.getCommunityName(), var17.getErpId());
						var18.setFieldColumnName("ddhb_one_erpId");
						int var27 = ((Integer) var19.get(var17.getInital())).intValue();
						searchField = (SearchField) fields.get(var27);
						Map var29 = searchField.getSubFiledsMap();
						List var33 = (List) var29.get("_");
						var33.add(var18);
					} else {
						searchField = new SearchField();
						searchField.setFieldName(var17.getInital());
						searchField.setFieldValue(var17.getInital());
						fields.add(searchField);
						var19.put(var17.getInital(), Integer.valueOf(fields.indexOf(searchField)));
						var25 = new LinkedHashMap();
						var18 = new SearchField(var17.getCommunityName(), var17.getCommunityName());
						var18.setFieldColumnName("ddhb_one_erpId");
						ArrayList var31 = new ArrayList();
						var31.add(var18);
						var25.put("_", var31);
						searchField.setSubFiledsMap(var25);
					}
				}

				return fields;
			} else if (subItems.get(0) instanceof Furniture) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					Furniture var16 = (Furniture) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var16.getFurName());
					searchField.setFieldValue(var16.getErpId());
					fields.add(searchField);
				}

				return fields;
			} else if (subItems.get(0) instanceof RentType) {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					RentType var15 = (RentType) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(var15.getRentTypeName());
					searchField.setFieldValue(String.valueOf(var15.getRentValue()));
					fields.add(searchField);
				}

				return fields;
			} else if (!(subItems.get(0) instanceof CityForNewHouse)) {
				return null;
			} else {
				cityForNewHouse = null;

				for (i = 0; i < subItems.size(); ++i) {
					cityForNewHouse = (CityForNewHouse) subItems.get(i);
					searchField = new SearchField();
					searchField.setFieldName(cityForNewHouse.getCityName());
					searchField.setFieldValue(String.valueOf(cityForNewHouse.getId()));
					subvalue = this.loadQuery(searchItem.getSubEntityName(),
							" entity.cityForNewHouse.id = " + cityForNewHouse.getId(), (String) null);
					countyList = new ArrayList();
					if (subvalue.size() > 0) {
						subFieldMap = null;
						subField = null;
						be1 = subvalue.iterator();

						while (be1.hasNext()) {
							be = (BaseEntity) be1.next();
							subFieldMap = (CountyForNewHouse) be;
							subField = new SearchField(subFieldMap.getCountyName(), String.valueOf(subFieldMap.getId()));
							subField.setFieldColumnName("ddhb_one_area.id");
							countyList.add(subField);
						}
					}

					var25 = new LinkedHashMap();
					if (countyList.size() > 0) {
						var25.put("_", countyList);
					}

					searchField.setSubFiledsMap(var25);
					fields.add(searchField);
				}

				return fields;
			}
		}
	}

}
