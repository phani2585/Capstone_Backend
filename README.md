# Capstone_Backend
SESSION ONE STARTS => 08-06-2019 02:48 AM 
CUSTOMERCONTROLLER => signup
                    =>login
                    =>logout
                    =>update
                    =>changepassword
                    -------------------------------------------
   SIGNUP=>                 
Update I => Draft version of the First API endpoint "signup - "/customer/signup" has been developed and added to repo.
Output looks like this as of now ., It is not upto the problem definition
![image](https://user-images.githubusercontent.com/44507567/59134112-c209cd00-8997-11e9-866f-2b940308320b.png)
Code has to be reviewed and changes has to be made to it to make it at par with the problem statement .
-------------------------------------------------------------------------------

![image](https://user-images.githubusercontent.com/44507567/59137271-a6f18a00-89a4-11e9-8d76-fd97c1956396.png)

Now API is working fine . ABLE TO CREATE USER USING SIGNUP SUCCESSFULLY.

But some work is pending in signup controller. i.e., checking for restrictions.
In the below picture , 
![image](https://user-images.githubusercontent.com/44507567/59137343-ffc12280-89a4-11e9-8138-1c02517b0b3c.png)

First two validations SGR-001 and SGR-005 are working fine.
As of now , SOME CODE HAS BEEN WRITTEN for rest three validations. CHECKING FORMAT OF EMAIL,CONTACT NUMBER AND PASSWORD.
----------------------------
Some one plz take up this (deepa) and use REGEX (Pattern/Matching) (suggested) or any other way to validate these three exceptions. LOOK BELOW for more details.PLZ HELP.
----------------------
![image](https://user-images.githubusercontent.com/44507567/59137441-8675ff80-89a5-11e9-84b6-a860177609bb.png)
------------
Input format for API Request body ..This is bcoz of @RequestBody(required = false) in customer controller
![image](https://user-images.githubusercontent.com/44507567/59137967-056c3780-89a8-11e9-8bef-314323cda798.png)
---------------------------
Pending tasks for signup: Three format validations code need to be written (assigned to deepa ) [08-06-2019 04:50 AM]
-----------------------------------------------------------------------------------------------------
LOGIN=> started looking into it ...
![image](https://user-images.githubusercontent.com/44507567/59139817-9563ae80-89b3-11e9-84f0-b51c3c4142bd.png)
Testing in progress[06:07AM]  ...Break ..=>
----------------------
Work resumes @ [06:31 AM]
-------------------------------
![image](https://user-images.githubusercontent.com/44507567/59140779-f2656180-89bf-11e9-8b9a-0c4d54e76307.png)
-----------------------------
Here contactNumber is used as username as per problem statement & password is "1234abcd56". So Base 64 encoding of contactnumber:password gives the below code which is used for Basic authentication (when user logsin for the first time)
![image](https://user-images.githubusercontent.com/44507567/59140798-0f019980-89c0-11e9-95df-c43905d874c8.png)
------------------------------
![image](https://user-images.githubusercontent.com/44507567/59140814-39ebed80-89c0-11e9-9766-2f6c84e68260.png)
Plz note accesstoken displayed in response header at the bottom ..(This accesstoken is used till user logsouts or when ever he signsin for second time or any other time.)

-----------------------------
![image](https://user-images.githubusercontent.com/44507567/59140974-c39cba80-89c2-11e9-925d-178c15e6c47f.png)
Two out of three exceptions are taken care of. Some one please write the code for third ATH-003 exception(@deepa).
![image](https://user-images.githubusercontent.com/44507567/59140988-f0e96880-89c2-11e9-9d59-254fc266b2d3.png)
----------------
First two exception working output result as below:
![login_excp_1](https://user-images.githubusercontent.com/44507567/59140996-15dddb80-89c3-11e9-839b-fd2f15fe42c1.JPG)
![login_excp_2](https://user-images.githubusercontent.com/44507567/59140998-1a09f900-89c3-11e9-81c1-7860e3afcb39.JPG)
LOGIN DONE => 1 pending task for deepa , plz define code for exception ATH-003 [07:59 AM]
------------------------------------------------------------------------------------
![image](https://user-images.githubusercontent.com/44507567/59141410-12018780-89ca-11e9-8f5e-868e5d376630.png)
Outputs:
![logout_exp_1](https://user-images.githubusercontent.com/44507567/59141411-1fb70d00-89ca-11e9-95ee-b30710b75dd2.JPG)
![logout_exp_2](https://user-images.githubusercontent.com/44507567/59141412-23e32a80-89ca-11e9-9c22-b52fe9eed837.JPG)
![logout_exps](https://user-images.githubusercontent.com/44507567/59141414-28a7de80-89ca-11e9-864b-35b4f4855a96.JPG)
--------------------
PLEASE CHECK IF ALL REQUIREMENTS ARE WORKING FINE.
-------------------------------------------------------------


SIGNUP=>LOGIN=>LOGOUT=> Need help with 4 pending tasks (Conditions for exceptions needs to be written and PLZ do TESTING for all the code written ) @Deepa
------------------------------------------------------------
[08:51 AM] SESSION ONE STARTS => 08-06-2019 02:48 AM  and ENDS 08:51 AM => 6 hrs approx 3 API's , more 19 => 19 * 2 hrs => 38 hrs => 38 hrs plus unplanned time 12 hrs 50 hrs => 5 days => 10 hrs per day  !!! Target ...Just need help from you when ever u can ..u n me can develop all the API's if we work as a Team..
--------------------------------------
11-06-2019 Latest update:

------------------
Note: First three code snippets are working fine (the ones u wrote for format checking of email, contactno and password)
Below is the code I write for pending task 4 .
-----------
If the Basic authentication is not provided in correct format, throw “AuthenticationFailedException” with the message code (ATH-003) and message (Incorrect format of decoded customer name and password) along with the corresponding HTTP status.
------------------------------
![image](https://user-images.githubusercontent.com/44507567/59226476-b238f580-8bf0-11e9-8908-1432cbb3c568.png)
---------------------------------------
For e.g., authentication is given as Basic MTIzNDU2Nzg5MDoxMjM0QGFiY2RF where username(contactno):password are 1234567890:1234@abcdE
I THINK =>We need to check for three conditions here if username/contactno is given in valid format , similarly password valid format and if Basic word is appended in the beginning.
I wrote code for 2 conditions. Please verify n write code to check Basic word is present or not or if the entire code i wrote is correct or not .
----------------------------------------
Update Customer working fine
----------------------------------
Change password working fine but some code needs to be added in order to encrypt and decrypt password .We can discuss and if u r fine u can take this up or i will do it if u find it difficult ...I m moving on to create enitities for diff tables .

-----------------------------------------
![image](https://user-images.githubusercontent.com/44507567/59232391-c0434200-8c01-11e9-8eee-623352726e15.png)
5 API's ready .  Necessary fixes need to be done and one round of Testing needs to be done. Ghanshyam n sanjay will do second round of testing ..
------------------------
