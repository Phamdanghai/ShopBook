/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipd.util;

/**
 *
 * @author haiph
 */
public class MyApplicationConstant {

    public static final String LOGIN_PAGE = "";
    public static final String LOGIN_SERVLET = "processLogin";
    public static final String SEARCH_LASTNAME_CONTROLLER = "processSearchLastname";
    public static final String DELETE_ACCOUNT_CONTROLLER = "processDeleteAccount";
    public static final String UPDATE_ACCOUNT_CONTROLLER = "processUpdateAccount";
    public static final String STARUP_CONTROLER = "processSatarup";
    public static final String ADD_BOOK_TO_CART = "processAddBookToCart";
    public static final String VIEW_CART_PAGE = "processViewCart";
    public static final String REMOVE_BOOK_FROM_CART = "processRemoveBookFromCart";
    public static final String CREATE_ACCOUNT_CONTROLLER = "processCreateAccount";

    public class AddBookToCartFeatures {

        public static final String SHOPPING_PAGE = "processBookShop";
    }

    public class CreateAccountFeatures {

        public static final String SIGNUP_PAGE = "processSignUp";
        public static final String LOGIN_PAGE = "";

    }

    public class DeleteFeatures {

        public static final String ERROR_PAGE = "processErroll";
    }

    public class LoginFeatures {

        public static final String INVALID_PAGE = "viewInvalidl";
        public static final String SEARCH_PAGE = "processSearch";
    }

    public class SatarUpFeatures {

        public static final String LOGIN_PAGE = "";
        public static final String SEARCH_PAGE = "processSearch";
    }

    public class SearchLastNameFeatures {

        public static final String SEAR_PAGE = "processSearch";
        public static final String SHOW_RESULT_PAGE = "processSearch";
    }

    public class UpdateAccountFeatures {

        public static final String ERROR_PAGE = "processErroll";
    }

    public class ListBookFeatures {

        public static final String SHOPING_PAGE = "viewShopping";
    }
    public class LogoutFeature{
        public static final String LOGOUT_PAGE = "loginpage";
    }
}
