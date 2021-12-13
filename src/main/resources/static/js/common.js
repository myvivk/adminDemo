function openLayer(url, title){
    $.ajaxSettings.async = false;
    $.get(url, function (res){
        layer.open({
            type:1,
            title:title,
            area:['800px','450px'],
            content:res
        });
    });
    $.ajaxSettings.async = true;
}

function mySubmit(filter,type){
    layui.form.on('submit('+filter+')', function(data){
        $.ajax({
            url:data.form.action,
            async:false,
            type:type,
            contentType:'application/json;charset=utf-8',
            data:JSON.stringify(data.field),
            success:function(res){
                if(res.code==0){
                    layer.closeAll();
                    query();
                }else{
                    layer.alert(res.msg);
                }
            }
        });
        return false;
    });
}

function myDelete(url){
    $.ajax({
        url:url,
        async:false,
        type:'DELETE',
        success:function(res){
            if(res.code==0){
                query();
            }else{
                layer.alert(res.msg);
            }
        }
    });
    layer.closeAll();
}