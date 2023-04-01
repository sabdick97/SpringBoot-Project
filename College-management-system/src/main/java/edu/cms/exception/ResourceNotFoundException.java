package edu.cms.exception;


	public class ResourceNotFoundException extends RuntimeException{

		private static final long serialVersionUID = 7769501835212716147L;

		private String resourceName;
		private String fieldname;
		private Object fieldvalue;
		
		public ResourceNotFoundException(String resourceName, String fieldname, Object fieldvalue) {
			super(String.format("%s not found with %s : '%s'",resourceName,fieldname,fieldvalue));
			this.resourceName = resourceName;
			this.fieldname = fieldname;
			this.fieldvalue = fieldvalue;
		}

		public String getResourceName() {
			return resourceName;
		}

		public void setResourceName(String resourceName) {
			this.resourceName = resourceName;
		}

		public String getFieldname() {
			return fieldname;
		}

		public void setFieldname(String fieldname) {
			this.fieldname = fieldname;
		}

		public Object getFieldvalue() {
			return fieldvalue;
		}

		public void setFieldvalue(Object fieldvalue) {
			this.fieldvalue = fieldvalue;
		}
		
		
	}


