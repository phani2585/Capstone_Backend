Rest API Endpoints-I (5+4=9 API's)
-----------------------------
Customer Controller
1.signup - "/customer/signup
2. login- "/customer/login"
3. logout - "/customer/logout"
4. Update - “/customer”
5. Change Password - “/customer/password”

Address Controller
"1.Save Address - “/address"
2.Get All Saved Addresses - “/address/customer”
3.Delete Saved Address - “/address/{address_id}”
4.Get All States - “/states”

---------------------------------------
Rest API Endpoints-II (5+1+2=8 API's)
=========================================
Restaurant Controller
1.Get All Restaurants - "/restaurant"
2.Get Restaurant/s by Name - “/restaurant/name/{reastaurant_name}”
3.Get Restaurants by Category Id “/restaurant/category/{category_id}”
4.Get Restaurant by Restaurant ID - “/api/restaurant/{restaurant_id}”
5.Update Restaurant Details- “/api/restaurant/{restaurant_id}”


Item Controller
1.Get Top 5 Items by Popularity - “/item/restaurant/{restaurant_id}


Category Controller
1.Get All Categories - “/category”
2.Get Category by Id - “/category/{category_id}”

--------------------------------------------------
Rest API Endpoints-III(3+1=4 API's)
===============================
Order Controller
1.Get Coupon by Coupon Name - “/order/coupon/{coupon_name}”
2.Get Past Orders of User - “/order”
3.Save Order - “/order”


Payment Controller
1.Get Payment Methods - “/payment”

---------------------------------------------------------
*The database password have been set as the same by all group members in application.yaml and localhost.properties file.
*Seperate business services have been implemented for all the API End Points to make sure all exceptions are checked for.
