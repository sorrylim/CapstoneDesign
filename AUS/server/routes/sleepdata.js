var express = require('express');
var router = express.Router();

var db_sleepdata = require('../public/SQL/sleepdata_sql')();

router.post('/get', function(req, res, next) {
    var user_id = req.body[0].user_id
    var sleep_date = req.body[0].sleep_date
    var nsleep_date = req.body[0].nsleep_date

    db_sleepdata.get_sleepdata(user_id, sleep_date, nsleep_date, function(err, result){
        if(err) console.log(err)
        else {
		res.send(result)
	
	}
    })
})

router.post('/get/sleepinfo', function(req, res, next) {
	var user_id = req.body.user_id
	var sleep_date = req.body.sleep_date

	db_sleepdata.get_sleepinfo(user_id, sleep_date, function(err, result) {
		if(err) console.log(err)
		else {
			res.send(result[0])
			console.log(result[0])
		}
	})
})



module.exports = router;
