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

}
