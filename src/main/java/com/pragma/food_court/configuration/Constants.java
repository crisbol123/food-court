package com.pragma.food_court.configuration;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }
 public enum entities {
       ARTICLE, CATEGORY, MARK
        }

    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";
    public static final String ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE = "The specified element does not exist";
    public static final String ELEMENT_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The %s already exists";
  public static final String EMAIL = "email";
  public static final String INVALID_OWNER_EXCEPTION_MESSAGE = "The owner does not exist";



    public static final String DISH_PATH = "/dish";
    public static final String CREATE_DISH_PATH = "/create";
    public static final String ENABLE_DISABLE_DISH_PATH = "/enable-disable/{id}";
    public static final String UPDATE_DISH_PATH = "/update/{id}";
    public static final String GET_ALL_DISHES_PATH = "/get-all";

    public static final String DISH_MANAGEMENT_TAG = "Dish Management";
    public static final String DISH_MANAGEMENT_DESCRIPTION = "API for managing dishes";


    public static final String CREATE_DISH_SUMMARY = "Create Dish";
    public static final String CREATE_DISH_DESCRIPTION = "Creates a new dish";
    public static final String ENABLE_DISABLE_DISH_SUMMARY = "Enable/Disable Dish";
    public static final String ENABLE_DISABLE_DISH_DESCRIPTION = "Enables or disables a dish by its ID";
    public static final String UPDATE_DISH_SUMMARY = "Update Dish";
    public static final String UPDATE_DISH_DESCRIPTION = "Updates the price and description of a dish";
    public static final String GET_ALL_DISHES_SUMMARY = "Get All Dishes";
    public static final String GET_ALL_DISHES_DESCRIPTION = "Retrieves a paginated list of dishes filtered by category and restaurant ID";

    public static final String ORDER_BASE_PATH = "/order";
    public static final String ORDER_TAG = "Order Management";
    public static final String ORDER_DESCRIPTION = "API for managing orders";

    public static final String ORDER_SAVE_PATH = "/save";
    public static final String ORDER_SAVE_SUMMARY = "Save Order";
    public static final String ORDER_SAVE_DESCRIPTION = "Creates and saves a new order";

    public static final String ORDER_GET_ALL_PATH = "/getAllOrders";
    public static final String ORDER_GET_ALL_SUMMARY = "Get All Orders";
    public static final String ORDER_GET_ALL_DESCRIPTION = "Retrieves a paginated list of orders filtered by status";

    public static final String ORDER_ASSIGN_PATH = "/assignOrder";
    public static final String ORDER_ASSIGN_SUMMARY = "Assign Order";
    public static final String ORDER_ASSIGN_DESCRIPTION = "Assigns an order to an employee";

    public static final String ORDER_NOTIFY_READY_PATH = "/notifyReadyOrder";
    public static final String ORDER_NOTIFY_READY_SUMMARY = "Notify Ready Order";
    public static final String ORDER_NOTIFY_READY_DESCRIPTION = "Notifies that an order is ready for pickup";

    public static final String ORDER_DELIVER_PATH = "/deliverOrder";
    public static final String ORDER_DELIVER_SUMMARY = "Deliver Order";
    public static final String ORDER_DELIVER_DESCRIPTION = "Delivers an order after verifying the security code";

    public static final String ORDER_CANCEL_PATH = "/cancelOrder";
    public static final String ORDER_CANCEL_SUMMARY = "Cancel Order";
    public static final String ORDER_CANCEL_DESCRIPTION = "Cancels an order";

    public static final String ORDER_GET_BY_CLIENT_ID_PATH = "/getOrdersIdByClientId";
    public static final String ORDER_GET_BY_CLIENT_ID_SUMMARY = "Get Orders by Client ID";
    public static final String ORDER_GET_BY_CLIENT_ID_DESCRIPTION = "Retrieves a list of order IDs associated with a client";

    public static final String ORDER_GET_ALL_IDS_PATH = "/getAllOrdersId";
    public static final String ORDER_GET_ALL_IDS_SUMMARY = "Get All Order IDs";
    public static final String ORDER_GET_ALL_IDS_DESCRIPTION = "Retrieves a list of all order IDs";

    public static final String ORDER_GET_ID_AND_EMPLOYEE_ID_PATH = "/getOrdersIdAndEmployeeId";
    public static final String ORDER_GET_ID_AND_EMPLOYEE_ID_SUMMARY = "Get Orders and Assigned Employees";
    public static final String ORDER_GET_ID_AND_EMPLOYEE_ID_DESCRIPTION = "Retrieves a map of order IDs and their assigned employee IDs";

    public static final String PAGE_PARAM = "page";
    public static final String SIZE_PARAM = "size";
    public static final String STATUS_PARAM = "status";
    public static final String ORDER_ID_PARAM = "orderId";
    public static final String CLIENT_ID_PARAM = "clientId";
    public static final String SECURITY_CODE_PARAM = "securityCode";


    public static final String RESTAURANT_BASE_PATH = "/restaurant";
    public static final String RESTAURANT_TAG = "Restaurant Management";
    public static final String RESTAURANT_DESCRIPTION = "API for managing restaurants";

    public static final String RESTAURANT_SAVE_PATH = "/save";
    public static final String RESTAURANT_SAVE_SUMMARY = "Save Restaurant";
    public static final String RESTAURANT_SAVE_DESCRIPTION = "Creates and saves a new restaurant";

    public static final String RESTAURANT_GET_ALL_PATH = "/get-all";
    public static final String RESTAURANT_GET_ALL_SUMMARY = "Get All Restaurants";
    public static final String RESTAURANT_GET_ALL_DESCRIPTION = "Retrieves a paginated list of all restaurants, sorted by name";

    public static final String RESTAURANT_CREATE_EMPLOYEE_PATH = "/create-employee";
    public static final String RESTAURANT_CREATE_EMPLOYEE_SUMMARY = "Create Employee";
    public static final String RESTAURANT_CREATE_EMPLOYEE_DESCRIPTION = "Assigns an employee to a restaurant";

    public static final String ASC_ORDER_BY_NAME_PARAM = "ascOrderByName";
}


