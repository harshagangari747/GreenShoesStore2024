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

	/* user account constants */
	public static String userAccountCreationSuccessfulEmailSubject = "Account Creation Successful!";
	public static String userAccountCreationSuccessfulEmailBody = "<p>Hello %s,</p>\r\n"
			+ "<img src=\"https://web-shoes-images-bucket.s3.amazonaws.com/rc-blue-1.png\"/>"
			+ "<p>Your account with GreenShoeStore is successfully registered. With an account you can manage your shopping detail, view orders and much more.&nbsp;</p>\r\n"
			+ "<p>GreenShoeStore is backed up by an NGO that makes a potential positive impact towards environment. We recycle plastic wastage which poses severe threat to our environment.</p>\r\n"
			+ "<p>Thankyou for your thoughts and participating in making our future sustainable.</p>\r\n"
			+ "<p>Regards,</p>\r\n" + "<p>Nakka Chowdara,</p>\r\n" + "<p>CEO @ GreenShoeStore&nbsp;</p>";

	/* sale product constants */;

	public static String onSaleProductErrorPostingAsSale = "Oops...Can't mark the product as sale.";
	public static String onSaleProductPriceIsGreaterThanCurrentPriceError = "The product you are trying to mark "
			+ "as sale has sale price higher than the current price. " + "Can't mark this product as on sale.";
	public static String onSaleProductCurrentPriceMisMatch = "The product you are trying to mark as sale has current price "
			+ "different than the current price of the product";
	public static String onSaleProductAlreadyExists = "This product already marked as in sale";
	public static String onSaleProductRevertError = "Can't mark this product as out of sale. Please contact dev team";
	public static String onSaleProductRevertSuccess = "Marked this products as out of sale";

	/* orders */
	public static String successOrderTableHeader = "<table><tbody><tr><th>Item</th><th>Quantity</th></tr>";

	public static String successOrderSingleRow = "<tr>%s</tr>";
	public static String successOrderSingleCell = "<td>%s</td>";

	public static String successOrderTotalFooter = "</tbody><tfoot></tfoot></table><br/>";
	public static String successOrderTotal = "<b>Order Total: $%s</b>";
	public static String successOrderMailNote = "<br/><p>Thankyou for shopping with us. This means that you are now a part of our team where we together build sustainable eco-friendly future for the forthcoming generations.</p>\r\n"
			+ "<p>We are proud of you for being a valuable &quot;Green&quot; customer and taking part in saving the lives of &nbsp;millions of aquatic friends. We believe that you keep this spirit and spread this thought to your friends and relatives.</p>\r\n"
			+ "<p>You have now won %s plastic coins as a token of appreciation. Collect 1000 plastic coins and redeem 2 products at our GreenShoeStore absolutely for free.</p>\r\n"
			+ "<p>Thank you,<br>Nakka Chowdara,<br>CEO@GreenShoes</p>";

	public static String orderSuccessMailSubject = "Order Success! ID: #%s";

}
