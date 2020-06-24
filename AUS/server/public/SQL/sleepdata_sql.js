var pool = require('../../config/db_config');

module.exports = function() {
    return {
        get_sleepdata: function(user_id, sleep_date, callback) {
            pool.getConnection(function(err, con){
                var sql = `select * from SLEEP_DATA where USER_ID='${user_id}' and DATE='${sleep_date}' `
                con.query(sql, function(err, result, fields){
                    con.release();
                    if(err) console.log(err);
                    else callback(null, result);
                })
            })
        },
        pool: pool
    }
};
