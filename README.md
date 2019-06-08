# Capstone_Backend
08-06-2019 02:48 AM 
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
