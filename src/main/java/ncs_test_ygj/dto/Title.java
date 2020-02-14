package ncs_test_ygj.dto;

public class Title {
	private String titleNo;
	private String titleName;

	public Title() {
		// TODO Auto-generated constructor stub
	}
	
	public Title(String titleNo) {
		this.titleNo = titleNo;
	}

	public Title(String titleNo, String titleName) {
		this.titleNo = titleNo;
		this.titleName = titleName;
	}
	
	public String getTitleNo() {
		return titleNo;
	}

	public void setTitleNo(String titleNo) {
		this.titleNo = titleNo;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titleNo == null) ? 0 : titleNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Title other = (Title) obj;
		if (titleNo == null) {
			if (other.titleNo != null)
				return false;
		} else if (!titleNo.equals(other.titleNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s, %s", titleNo, titleName);
	}
}
