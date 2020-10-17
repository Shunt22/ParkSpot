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
            var geometry = [[]];
            $.each(parkinglot.vertices, function( index1, vertex ) {
                geometry[0].push([vertex.x,vertex.y]);
            });
            var p = new ymaps.Polygon(geometry);
            map.geoObjects.add(p);
        });
    });
}