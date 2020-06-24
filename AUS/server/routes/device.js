var express = require('express');
var router = express.Router();

var db_device = require('../public/SQL/device_sql')();

router.post('/', function(req, res, next) {
    var user_id = req.body[0].user_id
    var irsignal = req.body[0].irsignal

    db_device.check_signal(function(err, result){
        if(err) console.log(err)
        else {
            var object = new Object()
            object.result = "success"
            res.send(object)
        }
    })
})
//나중에 디비에 ir신호를 직접 넣을수 있게 되면 그냥 post를 통해 앱으로 ir신호를 가져와서 다시 insert하는 방식으로 할 것

router.post('/get', function(req, res, next){
    var comp_name = req.body[0].comp_name
    var device_name = req.body[0].device_name

    db_device.get_irsignal(comp_name, device_name, function(err, result){
        if(err) console.log(err)
        else res.send(result)
    })
})

router.post('/insert', function(req, res, next){
    var user_id = req.body[0].user_id
    var irsignal = req.body[0].irsignal
    
    db_device.insert_irsignal(user_id, irsignal, function(err, result){
        if(err) console.log(err)
        else {
            var object = new Object()
            object.result = "success"
            res.send(object)
        }
    })
})

module.exports = router;