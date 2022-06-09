
$(document).ready(function(){
    $('#search').click(function(e) {
        let selectValueRole = $("#role_select").val();
        let selectValueBlocked = $("#blocked").val();
        const link = window.location.origin;
        if(selectValueRole!=="" && selectValueBlocked!=="") {
            window.location.replace(link +"/filter/"+selectValueRole + "/" + selectValueBlocked);
        }else {
            window.location.replace(link);
        }
    });
});
