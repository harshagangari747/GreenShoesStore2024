package com.store.greenShoes.Constants;

public class Constants {
	public static String resetPasswordBodyTemplate = "You have triggered password reset.<br/>"
			+ "<br/>Please note your temporary password: <b> %s </b> <br/> "
			+ "Please reset your password as soon as possible.</br> " + "Thank you,<br/> admin@ Green Shoe Store<br/>";

	public static String resetPasswordEmailSubject = "Important! Password reset triggered";
	public static String resetPasswordEmailSentSuccessful = "Password was sent to the email provided successfully";
	public static String resetPasswordEmailDeliveryFailed = "Something went wrong! Password was not sent. Please try again";
	public static String resetPasswordError = "Password reset failed. Possibly it could be because %s";
	public static String resetPasswordUserDoesntExist = "Ohhoo... Wait a minute, this user %s doesn't exists.";
	
	/*user account constants*/
	public static String userAccountCreationSuccessfulEmailSubject = "Account Creation Successful!";
	public static String userAccountCreationSuccessfulEmailBody = "<p>Hello %s,</p>\r\n"
			+ "<img src=\"https://web-shoes-images-bucket.s3.amazonaws.com/rc-blue-1.png\"/>"
			+ "<p>Your account with GreenShoeStore is successfully registered. With an account you can manage your shopping detail, view orders and much more.&nbsp;</p>\r\n"
			+ "<p>GreenShoeStore is backed up by an NGO that makes a potential positive impact towards environment. We recycle plastic wastage which poses severe threat to our environment.</p>\r\n"
			+ "<p>Thankyou for your thoughts and participating in making our future sustainable.</p>\r\n"
			+ "<p>Regards,</p>\r\n"
			+ "<p>Nakka Chowdara,</p>\r\n"
			+ "<p>CEO @ GreenShoeStore&nbsp;</p>";

}
