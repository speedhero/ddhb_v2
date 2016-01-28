package com.huatek.framework.security;

import com.huatek.framework.entity.FwAccountDuty;
import com.huatek.framework.security.ThreadLocalClient;
import java.util.Iterator;

public class GlobalFilterManagement {
	public static String getSystemGroupFilter(String aliaName) {
		if (ThreadLocalClient.get() != null && ThreadLocalClient.get().getOperator() != null) {
			Iterator fwDutyIterator = ThreadLocalClient.get().getOperator().getFwAccountDuties().iterator();

			while (fwDutyIterator.hasNext()) {
				if (((FwAccountDuty) fwDutyIterator.next()).getFwDuty().getId().longValue() == 1L) { return " 1=1"; }
			}
		}

		return " " + aliaName + ".fwGroup.id>=1";
	}

	public static String getGroupFilter(String aliaName) {
		if (ThreadLocalClient.get() != null && ThreadLocalClient.get().getOperator() != null) {
			Iterator fwDutyIterator = ThreadLocalClient.get().getOperator().getFwAccountDuties().iterator();

			while (fwDutyIterator.hasNext()) {
				if (((FwAccountDuty) fwDutyIterator.next()).getFwDuty().getId().longValue() == 1L) { return " 1=1"; }
			}
		}

		return " " + aliaName + ".id>=2";
	}
}
