package application;

public enum EnumPHP {

	GET_CATEGORIAS("getAllCategory.php"), GET_ALL_PRODUCTS("getAllProducts.php"), INSERT_PRODUCTO("insertProducto.php");
	
	private String PHP_File;
	
	private EnumPHP(String PHP_File) {
		
		this.PHP_File = PHP_File;
		
	}
	
	public String getPHP() {
		return PHP_File;
	}
	
}
