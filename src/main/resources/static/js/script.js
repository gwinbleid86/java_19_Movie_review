const baseUrl = 'http://localhost:8089/api/movies'
window.addEventListener('load', async () => {
    let url = `${baseUrl}/paging?page=0&pageSize=3`
    await fillMovies(url)
})

async function fillMovies(url) {
    let response = await makeRequest(url)
    let data = await response.json()

    moviesProcessing(data.movies)
    makePagination(data.totalPage, data.page + 1)
}

async function makeRequest(url, method = 'get') {
    return await fetch(url, {method})
}

function moviesProcessing(response) {
    let contentDiv = document.getElementById('content')
    let rowDiv = document.createElement('div')
    rowDiv.id = 'row-div'
    rowDiv.className = 'row'
    contentDiv.appendChild(rowDiv)

    for (let e of response) {
        makeCard(e)
    }
}

function makeCard(data) {
    let contentDiv = document.getElementById('row-div')

    let colDiv = document.createElement('div')
    colDiv.className = 'col m-2'
    contentDiv.appendChild(colDiv)

    let card = document.createElement('div')
    card.className = 'card'
    card.style.width = '18rem'
    card.style['min-height'] = '200px'
    colDiv.appendChild(card)

    let cardBody = document.createElement('div')
    cardBody.className = 'card-body'
    card.appendChild(cardBody)

    let title = document.createElement('h5')
    title.className = 'card-title'
    let titleContent = document.createTextNode(data.name)
    title.appendChild(titleContent)
    cardBody.appendChild(title)

    let subTitle = document.createElement('h6')
    subTitle.className = 'card-subtitle mb-2 text-muted'
    let subTitleContent = document.createTextNode(data.release_year)
    subTitle.appendChild(subTitleContent)
    cardBody.appendChild(subTitle)

    let director = document.createElement('p')
    director.className = 'card-text'
    let directorContent = document.createTextNode(data.director.fullName)
    director.appendChild(directorContent)
    cardBody.appendChild(director)

    let linkInfo = document.createElement('a')
    linkInfo.className = 'card-link'
    linkInfo.href = `/info/${data.id}`
    let linkContent = document.createTextNode('More info')
    linkInfo.appendChild(linkContent)
    cardBody.appendChild(linkInfo)
}

function makePagination(totalPage, page) {
    let paginationDiv = document.getElementById('pagination')
    paginationDiv.innerHTML = ''

    let prevDiv = document.createElement('li')
    prevDiv.className = 'page-item'
    paginationDiv.appendChild(prevDiv)
    prevDiv.appendChild(makeLinkBlock(-1, 'Previous'))

    if (page === 1) {
        prevDiv.classList.add('disabled')
        prevDiv.children[0]['aria-disabled'] = true
    }

    for (let i = 0; i < totalPage; i++) {
        makePaginationButton(i, i === page - 1)
    }

    let nextDiv = document.createElement('li')
    nextDiv.className = 'page-item'
    paginationDiv.appendChild(nextDiv)
    nextDiv.appendChild(makeLinkBlock('+1', 'Next'))

    if (page === totalPage) {
        nextDiv.classList.add('disabled')
        nextDiv.children[0]['aria-disabled'] = true
    }

}

function makeLinkBlock(tabIndex, textNode) {
    let link = document.createElement('a')
    link.className = 'page-link'
    link.href = '#'
    link.tabIndex = tabIndex
    link.onclick = goToPage
    let linkContent = document.createTextNode(textNode)
    link.appendChild(linkContent)
    return link
}

function makePaginationButton(page, isActive) {

    let paginationDiv = document.getElementById('pagination')
    let nextNav = document.getElementById('next')

    let liDiv = document.createElement('li')
    liDiv.className = 'page-item'
    if (isActive) {
        liDiv.classList.add('active')
        liDiv['aria-current'] = 'page'
    }
    paginationDiv.insertBefore(liDiv, nextNav)

    liDiv.appendChild(makeLinkBlock(page, page + 1))
}


function goToPage() {
    let pagination = document.getElementById('pagination')
    let activeLi = pagination.getElementsByClassName('active')[0]
    let currentPage = activeLi.children[0].tabIndex
    console.log(currentPage);

    let a = this
    let tabIndex = a.tabIndex
    if (tabIndex === +1) {
        currentPage++
    } else if (tabIndex === -1) {
        currentPage--
    } else {
        currentPage = a.innerHTML - 1
    }

    let url = `${baseUrl}/paging?page=${currentPage}&pageSize=3`
    console.log(url);
    let contentDiv = document.getElementById('content')
    contentDiv.replaceChildren()

    fillMovies(url)
}