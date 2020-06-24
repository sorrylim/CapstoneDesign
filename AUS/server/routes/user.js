var express = require('express');
var router = express.Router();

var db_user = require('../public/SQL/user_sql')();

router.post('/insert', function(req, res, next){
  var user_id = req.body[0].user_id
  var user_pw = req.body[0].user_pw
  var user_name = req.body[0].user_name

  db_user.insert_user(function(err, result){
      if(err) console.log(err)
      else {
        var object = new Object()
        object.result = "success"
        res.send(object)
      }
  })
})

router.post('/login', function(req, res, next){
  var user_id = req.body[0].user_id

  db_user.login(user_id, function(err, result){
    if(err) console.log(err)
    else {
      var data = result[0]
      res.send(data)
    }
  })
})

module.exports = router;
