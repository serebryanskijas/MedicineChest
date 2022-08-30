function sort() {
    var categoryId = document.getElementById('category').value;
    var nameSelect = document.getElementById('name');

    var httpRequest;
    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
        httpRequest = new XMLHttpRequest();
        httpRequest.overrideMimeType('text/xml');
        httpRequest.onreadystatechange = function () {
            if (httpRequest.readyState == 4 && httpRequest.status == 200) {
                var medicineIdArr = JSON.parse(httpRequest.responseText).result;
                for (var i = 0; i < nameSelect.options.length; i++) {
                    var id = Number(nameSelect.options[i].value);
                    if(medicineIdArr.includes(id) || i==0){
                        nameSelect.options[i].hidden = false;
                    }else {
                        nameSelect.options[i].hidden = true;
                    }
                    nameSelect.options[i].selected=false;
                }
            } else {

            }
        };
        httpRequest.open('GET', 'http://localhost:8380/getMedicineByCategory?categoryId=' + categoryId, false);
        httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        httpRequest.send();
    }

}
