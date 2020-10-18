var map;
$(function(){
    ymaps.ready(init);
});

function init(){
    // Создание карты.
    map = new ymaps.Map("map", {
        // Координаты центра карты.
        // Порядок по умолчанию: «широта, долгота».
        // Чтобы не определять координаты центра карты вручную,
        // воспользуйтесь инструментом Определение координат.
        center: [30, 50],
        // Уровень масштабирования. Допустимые значения:
        // от 0 (весь мир) до 19.
        zoom: 4
    });
    $.getJSON("/returnAll")
    .done(function(data) {
        $.each( data, function( index, parkinglot ) {
            var geometry = [];
            $.each(parkinglot.vertices, function( index1, vertex ) {
                geometry.push([vertex.x,vertex.y]);
            });
            var parkingLotRectangle = new ymaps.Rectangle(geometry);
            var parkingLotInfo = parkinglot.name+"\nWorking time: "+parkinglot.timeStart+"-"+parkinglot.timeFinish;
            parkingLotRectangle.properties={
                hintContent: parkinglot.name,
                balloonContent: parkingLotInfo
            }
            map.geoObjects.add(parkingLotRectangle);
        });
    });
    var parkingLot;
    $("#addNewButton").click(function(){
        $("#addNewDiv").show();
        map.geoObjects.remove(parkingLot);
        // Создаем многоугольник в виде прямоугольника.
        parkingLot = new ymaps.Polygon([
            [
                [30, 40],
                [30, 50],
                [40, 50],
                [40, 40],
                [30, 40]
            ]
        ]);
        // Добавляем многоугольник на карту.
        map.geoObjects.add(parkingLot);

        // Включаем режим масштабирования.
        parkingLot.editor.startFraming();
    });
    $("#submitNewButton").click(function(event){
        event.preventDefault();
        if(parkingLot===undefined){
            return;
        }

        var object={}
        object.vertices=[];
        $.each(parkingLot.geometry._bounds, function(index,data){
            object.vertices.push({x:data[0],y:data[1]});
        });
        object.name=$("#newParkingLotName").val();
        object.timeStart=$("#timeStart").val();
        object.timeFinish=$("#timeFinish").val();

        parkingLot.properties={
                        hintContent: object.name,
                        balloonContent:object.name
        }
        $.ajax({
            url: "/addNew",
            type: "POST",
            data: JSON.stringify(object),
            dataType: 'text',
            async: true,
            contentType: 'application/json; charset=utf-8',
        })
        .done(function(){
            console.log("DONE");
        })
        .fail(function(){
               console.log("FAIL");
        });
        parkingLot.editor.stopFraming();
        $("#addNewDiv").hide();
        parkingLot=null;
    });
    
    $("#24Hours").change(function() {
        // this will contain a reference to the checkbox
        if (this.checked) {
            $("#timeStart" ).attr('disabled', 'disabled');
            $("#timeFinish").attr('disabled', 'disabled');
        } else {
            $("#timeStart" ).removeAttr('disabled');
            $("#timeFinish").removeAttr('disabled');
        }
    });

}


