var pool = require('../../config/db_config');

module.exports = function () {
    return {
        
        login: function(user_id,callback){
            pool.getConnection(function(err,con){
                var sql=`select * from USER where USER_ID='${user_id}'`
                con.query(sql, function(err,result,fields){
                    con.release();
                    if(err) console.log(err);
                    else callback(null,result);
                })
            })
        },

        join: function (id, password,temperate, callback) {
            pool.getConnection(function (err, con) {
              var sql = `insert into USER values('${id}','${password}','${temperate}')`
              con.query(sql, function (err, result) {
                con.release()
                if (err) callback(err)
                else callback(null, result)
              })
            })
          },
        pool: pool
    }
};
