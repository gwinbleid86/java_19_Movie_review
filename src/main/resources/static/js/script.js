window.addEventListener('load', () => {
    let xhr = new XMLHttpRequest();

    xhr.onload = function () {
        console.log(xhr.status)
        console.log(typeof xhr.response)

        xhr.response.blob().then(function (blob) {
            let objectURL = URL.createObjectURL(blob);
            loadImage(objectURL);
        });

        // let img = btoa(xhr.response)
        // let img = convertImage(xhr.response)
        // loadImage(img)
    }

    xhr.open('get', 'http://localhost:8089/images/4')
    xhr.send()

})

function loadImage(data) {
    let body = document.body
    let img = document.createElement('img') // $('#id')
    img.src = `${data}`
    body.append(img)
}

function convertImage(data) {
    return URL.createObjectURL(atob(data))
}