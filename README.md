### HotelApp

* Example Basic Web Application using JSP / Maven / Postgres
* Heroku support in the heroku-deploy tag

### Heroku how to:

    git clone [thisrepo]
    cd hotelapp-paw
    git checkout heroku-deploy
    
    # Cedar is currently the only heroku stack that supports java
    heroku create [yourapp] --stack cedar 
    
    # You must have the app's schema loaded
    # My [connectionUrl] was: postgres://cris:holahola@localhost:5432/hotelapp
    heroku db:push [connectionUrl] --confirm [yourapp]
    
    # heroku-deploys' branch has a different setting in the pom dependencies
    git push heroku heroku-deploy:master