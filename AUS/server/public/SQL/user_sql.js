var pool = require('../../config/db_config');

module.exports = function () {
    return {
        insert_user: function(user_id, user_pw, user_name){
            pool.getConnection(function(err,con){
                var sql=`insert into user values('${id}','${pw}','${name}')`
                con.query(sql, function(err,result,fields){
                    con.release();
                    if(err) console.log(err);
                    else console.log("회원가입 완료");
                })
            })
        },

        login: function(id,callback){
            pool.getConnection(function(err,con){
                var sql=`select * from user where user_id='${id}'`
                con.query(sql, function(err,result,fields){
                    con.release();
                    if(err) console.log(err);
                    else callback(null,result);
                })
            })
        }
        ,
        pool: pool
    }
};
