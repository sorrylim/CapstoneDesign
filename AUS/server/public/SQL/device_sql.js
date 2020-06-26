var pool = require('../../config/db_config');

module.exports = function() {
    return {
        get_irsignal: function(comp_name, device_name, callback) {
            pool.getConnection(function(err, con){
                var sql = `select * from device where comp_name = '${comp_name}' && device_name = '${device_name}'`
                con.query(sql, function(err, result, fields){
                    con.release();
                    if(err) console.log(err);
                    else callback(null, result);
                })
            })
        },
        insert_irsignal: function(user_id, irsignal, callback){
            pool.getConnection(function(err, con){
                var sql = `update user set irsignal = '${irsignal}' where user_id = '${user_id}'`
                con.query(sql, function(err, result, fields){
                    con.release();
                    if(err) console.log(err);
                    else console.log("ir신호등록완료.");
                })
            })
        },

        check_device: function(callback){
            pool.getConnetion(function(err,con){
            var sql=`select * from DEVICES`
                con.query(sql,function(err,result,fields){
                con,release()
                    if(err)console.log(err);
                    else callback(null, result)
                })
            })
        },

        pool: pool
    }
};