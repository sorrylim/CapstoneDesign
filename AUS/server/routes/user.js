var express = require('express');
var router = express.Router();

var db_user = require('../public/SQL/user_sql')();


router.post('/login', function(req, res, next){
  var user_id = req.body[0].id

  db_user.login(user_id, function(err, result){
    if(err) console.log(err)
    else {
      var data = result[0]
      res.send(data)
    }
  })
})

router.post('/join', function (req, res, next) {
  var id = req.body.ID
  var password = req.body.PASSWORD
  var temperate = req.body.TEMPERATE
 
  db_user.join(id, password,temperate, function (err, result) {
    if (err) console.log(err)
    else {
      var result = new Object()
      result.result = "success"
      console.log("회원가입")
      res.send(result)
    }
  })
})
module.exports = router;
