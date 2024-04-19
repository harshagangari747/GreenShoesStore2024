package com.store.greenShoes.Constants;

public class Constants {
	/* rest password constants */
	public static String resetPasswordBodyTemplate = "You have triggered password reset.<br/>"
			+ "<br/>Please note your temporary password: <b> %s </b> <br/> "
			+ "Please reset your password as soon as possible.</br> " + "Thank you,<br/> admin@ Green Shoe Store<br/>";

	public static String resetPasswordEmailSubject = "Important! Password reset triggered";
	public static String resetPasswordEmailSentSuccessful = "Password was sent to the email provided successfully";
	public static String resetPasswordEmailDeliveryFailed = "Something went wrong! Password was not sent. Please try again";
	public static String resetPasswordError = "Password reset failed. Possibly it could be because %s";
	public static String resetPasswordUserDoesntExist = "Ohhoo... Wait a minute, this user %s doesn't exists.";

	/* sale product constants */;

	public static String onSaleProductErrorPostingAsSale = "Oops...Can't mark the product as sale.";
	public static String onSaleProductPriceIsGreaterThanCurrentPriceError = "The product you are trying to mark "
			+ "as sale has sale price higher than the current price. " + "Can't mark this product as on sale.";
	public static String onSaleProductCurrentPriceMisMatch = "The product you are trying to mark as sale has current price "
			+ "different than the current price of the product";
	public static String onSaleProductAlreadyExists = "This product already marked as in sale";
	public static String onSaleProductRevertError = "Can't mark this product as out of sale. Please contact dev team";
	public static String onSaleProductRevertSuccess = "Marked this products as out of sale";

}
