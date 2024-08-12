package genericutilities;

/**
 * this enum stores all the tab names of vtiger application
 */

public enum TabNames {
	ORGANIZATION("Accounts"), CONTACTS("Contacts"), LEADS("Leads");
	private String tabName;
	private TabNames(String tabName) {
		this.tabName = tabName;
	}
	public String getTabName() {
		return tabName;
	}
}
