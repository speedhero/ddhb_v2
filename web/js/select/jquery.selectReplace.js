;(function($){
  $.fn.extend({
    "replaceAllSelect" : function(){
      $(this).find("select").each(function(){
        var selectObj = $(this);
        var selectObjId = $(this).attr("id");
        var selectOptions = new Array();
        $(this).find("option").each(function(){
          selectOptions.push($(this));
        });
	    var hasRelative = true;
	    var hasDiableFlag = false;
	    var disableValue = false;
	    if (typeof($(this).attr("relativeSelectId")) == 'undefined'){
	      hasRelative = false;
	    }
	    if ($(this).attr("isDisableFlag") == 'true' || $(this).attr("isDisableFlag") == 'false'){
	      hasDiableFlag = true;
	      disableValue = $(this).attr("isDisableFlag") == 'true' ? true : false;
	    }
        var newSelectObj = createSelectObj(selectObjId, selectOptions, hasRelative, hasDiableFlag, disableValue);
        $(this).parent().append(newSelectObj);
        $(this).css("display", "none");
      });
      
      function createSelectObj(selectObjId, selectOptions, hasRelative, hasDiableFlag, disableValue){
        var cSearch = $("<div class='cSearch' id='" + ("select_" + selectObjId) + "'></div>");
        var valueContainer;
        if (hasDiableFlag){
          valueContainer = $("<div class='valueContainer' isDisableFlag='" + disableValue + "'><div class='selectedValue'><span class='valueSpan' style='margin-left:10px;'></span></div><div class='xlicon'></div></div><div style='clear:both;'></div>");
        }else{
          valueContainer = $("<div class='valueContainer'><div class='selectedValue'><span class='valueSpan' style='margin-left:10px;'></span></div><div class='xlicon'></div></div><div style='clear:both;'></div>");
        }
        $(cSearch).append(valueContainer);
        var xlxx = $("<div class='xlxx'></div>");
        $(cSearch).append(xlxx);
        var optionStr = "";
        for(var i = 0; i < selectOptions.length; i++){
          if (i == 0){
            $(valueContainer).find(".valueSpan").html(selectOptions[i].text());
          }
          optionStr += "<a optionValue='" + selectOptions[i].val() + "' hasRelative='" + hasRelative + "' selectId='" + selectObjId + "'><span style='margin-left:10px;'>" + selectOptions[i].text() + "</span></a>";
        }
        $(xlxx).append(optionStr);
        return cSearch;
      }
      
      $(".cSearch a").click(function(){
        $(this).parent().css("display", "none");
        $(this).parent().parent().find(".selectedValue").html($(this).html());
        var selecctedValue = $(this).attr("optionvalue");
        var selectObjId = $(this).attr("selectId");
        $("#" + selectObjId).val(selecctedValue);
        if ($(this).attr("hasRelative") == 'true'){
          changeRelativeSelect(selecctedValue);
        }
      });
      
      $(".valueContainer").click(function(){
        var xlObj = $(this).parent().find(".xlxx");
        if ($(this).attr("isdisableflag") == 'true'){
          return;
        }
        if ($(xlObj).css("display") == 'block'){
          $(xlObj).css("display", "none");
        }else{
          $(xlObj).css("display", "block");
        }
      });
    },
    
	"replaceSelect":function(){
	    var container = $(this).parent();
	    $(container).find("a").remove();
	    var selectedObj = $(container).find("select").first();
	    if (selectedObj.length <= 0){
	      return;
	    }
	    var selectOptions = new Array();
	    $(this).find("option").each(function(){
	      selectOptions.push($(this));
	    });
	    var selectObjId = $(this).attr("id");
	    var optionStr = "";
	    for (var i = 0; i < selectOptions.length; i++){
	      optionStr += "<a optionValue='" + selectOptions[i].val() + "' selectId='" + selectObjId + "'><span style='margin-left:10px;'>" + selectOptions[i].text() + "</span></a>";
	    }
	    $(container).find(".xlxx").first().append(optionStr);
	    $(container).find(".selectedValue").html("<span style='margin-left:10px;'>" + selectOptions[0].text() + "</span>");
	    $(this).val(selectOptions[0].val());
	    $(".cSearch a").click(function(){
	      $(this).parent().css("display", "none");
	      $(this).parent().parent().find(".selectedValue").html($(this).html());
	      var selecctedValue = $(this).attr("optionvalue");
	      var selectObjId = $(this).attr("selectId");
	      $("#" + selectObjId).val(selecctedValue);
	    });
      }
  });
  
  //console.log("<<<<<<<<<<<<<<<<<<<<<<<<<");
  //console.log($.fn.replaceAllSelect);
})(jQuery);