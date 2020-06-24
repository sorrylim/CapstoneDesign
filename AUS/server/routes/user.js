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
  var id = req.body.id
  var password = req.body.password
  var temperature = req.body.temperature
 
  db_user.join(id, password,temperature, function (err, result) {
    if (err) console.log(err)
    else {
      var result = new Object()
      result.result = "success"
      console.log("회원가입")
      res.send(result)
    }
  })
})

router.post('/id_check', function (req, res, next) {
  var id = req.body.id
 
  db_user.id_check(id,function (err, result) {
    if (err) console.log(err)
    else {
      var data=result[0]
      res.send(data)
    }
  })
})
module.exports = router;
