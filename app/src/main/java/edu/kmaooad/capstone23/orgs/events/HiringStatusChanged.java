package edu.kmaooad.capstone23.orgs.events;

import edu.kmaooad.capstone23.orgs.dal.HiringStatus;

public class HiringStatusChanged {

        private HiringStatus hiringStatus;

        public String getHiringStatus() {
            return hiringStatus;
        }

        public HiringStatusChanged(HiringStatus hiringStatus) {
            this.hiringStatus = hiringStatus;
        }


}
