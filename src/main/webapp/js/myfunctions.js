function sendAjax() {

    var esIndex = new Object();
    esIndex.name = $('#name').val();
    console.log(esIndex);
    esIndex.description = $('#description').val();
    esIndex.categories = $('#categories').val().split(";");
    esIndex.tags = $('#tags').val().split(";");

    $.ajax({
        url: "/json",
        type: "POST",
        dataType: "json",
        data: JSON.stringify(esIndex),
        contentType: "application/json",
        mimeType: "application/json",


        success: function (data) {

            $("tr:has(td)").remove();

            $.each(data, function (index, esIndex) {
                console.log(esIndex.categories);

                var td_description = $("<td/>");
                    var span = $("<span class='label label-info' style='margin:4px;padding:4px' />");
                    span.text(esIndex.description);
                    td_description.append(span);


                var td_categories = $("<td/>");
                $.each(esIndex.categories, function (i, tag) {

                    var span = $("<span class='label label-info' style='margin:4px;padding:4px' />");
                    span.text(tag);
                    td_categories.append(span);
                });

                var td_tags = $("<td/>");
                $.each(esIndex.tags, function (i, tag) {
                    var span = $("<span class='label' style='margin:4px;padding:4px' />");
                    span.text(tag);
                    td_tags.append(span);
                });

                $("#parsed").append($('<tr/>')
                        .append($('<td/>').html("<a href='"+esIndex.name+"'>"+esIndex.name+"</a>"))
                        .append(td_description)
                        .append(td_categories)
                        .append(td_tags)
                );

            });
        },

        error:function(data,status,er) {
            alert("error: "+data+" status: "+status+" er:"+er);
        }
    });
}