package nl.hva.makeitwork.bankit.bankitapplication.model.user;

public enum Position {
    ACCOUNTMANAGER {
        @Override
        public String toString() {
            return "Accountmanager";
        }
    },
    HEAD_BUSINESS {
        @Override
        public String toString() {
            return "Head Business";
        }
    },
    HEAD_PRIVATE {
        @Override
        public String toString() {
            return "Head Private";
        }
    }

}
