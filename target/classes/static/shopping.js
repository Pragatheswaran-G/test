let role = localStorage.getItem("role");
let products = [];
if (role !== "Admin") {
    document.getElementById("uploadbutton").style.display = "none";
}
function openUploadPage() {
    // window.location.href = "shoppingUpload.html";
    window.location.replace("shoppingUpload.html");
}
async function getProduct() {
    let ans = document.getElementById("card_container");
    const response = await fetch(
        "http://localhost:8080/api/product/show",
        {
            method: "GET"
        }
    );
    products = await response.json();
    let app = "";
    products.forEach(e => {
        app += `
            <div class="card">
                <div class="image_res">
                    <img src="http://localhost:8080/images/${e.imageUrl}" alt=""> 
                </div> 
                <div class="content"> 
                    <div class="content_head"> 
                        <h2>${e.name}</h2> 
                        <h3>NTQ : ${e.quantity}</h3> 
                    </div> 
                    <p>${e.details}</p> 
                    <div class="purchase"> 
                        <h3>₹${e.price}</h3> 
                        <button id="card" onclick="addCard(${e.id})">Add Cart</button> 
                    </div> 
                    <button id="buy" onclick="buyButton(${e.price},${e.id},${e.quantity})">Buy</button> 
                </div>
            </div>`;
    });

    ans.innerHTML = app;
}
async function addCard(productId) {
    let id = localStorage.getItem("id");
    const response = await fetch(
        `http://localhost:8080/api/cart/add?userId=${id}&productId=${productId}`,
        {
            method: "POST"
        }
    );
    const data = await response.text();
    alert(data);
}
async function uploadProduct() {
    let formdata = new FormData();
    formdata.append("name", document.getElementById("name").value);
    formdata.append("details", document.getElementById("details").value);
    formdata.append("price", document.getElementById("price").value);
    formdata.append("quantity", document.getElementById("quantity").value);
    formdata.append("image", document.getElementById("image").files[0]);
    if (document.getElementById("image").files.length === 0) {
        alert("Please select an image");
        return;
    }
    const response = await fetch(
        "http://localhost:8080/api/product/add",
        {
            method: "POST",
            body: formdata
        }
    );
    const data = await response.text();
    if (data === "Product Added Successfully") {
        location.reload();
        getcard();
    }
}
function buyButton(price, productId, productquantity) {
    localStorage.setItem("price", price);
    localStorage.setItem("productId", productId);
    localStorage.setItem("productquantity", 1);
    // window.location.href = "buy.html";
    window.location.replace("buy.html");
}
async function paid(productId, quantity) {
    const response = await fetch(
        `http://localhost:8080/api/cart/update?productId=${productId}&quantity=${quantity}`,
        {
            method: "PUT"
        }
    );

    const data = await response.text();
}
async function buy_Button() {
    let id = localStorage.getItem("id");
    const response = await fetch(
        `http://localhost:8080/api/cart/show/${id}`,
        {
            method: "GET"
        }
    );
    const data = await response.json();
    for (const e of data) {

        await paid(e.id, e.cartNqt);

        for (let i = 0; i < e.cartNqt; i++) {
            await removeCart(e.id);
        }

    }

}
async function removeCart(productId) {
    let id = localStorage.getItem("id");
    const response = await fetch(
        `http://localhost:8080/api/cart/delete?userId=${id}&productId=${productId}`,
        {
            method: "DELETE"
        }
    );
    const data = await response.text();
    console.log(data);
    await getcard();
}
async function getcard() {
    let id = localStorage.getItem("id");
    let ans = document.getElementById("card_container");
    if (!ans) {
        return;
    }
    const response = await fetch(
        `http://localhost:8080/api/cart/show/${id}`,
        {
            method: "GET"
        }
    );
    const data = await response.json();
    let app = "";
    let price = 0;
    data.forEach(e => {
        if (e.cartNqt <= e.quantity) {
            price += (e.price * e.cartNqt);
        }
        else {
            price += (e.price * e.quantity);
        }
        app += `
        <div class="card">
            <div class="image_res">
                <img src="http://localhost:8080/images/${e.imageUrl}" alt="">
            </div>
            <div class="content">
                <div class="content_head">
                    <h2>${e.name}</h2>
                    <h3>NTQ : ${e.quantity}</h3>
                </div>
                <p>${e.details}</p>
                <div class="purchase">
                    <h3>₹${e.price}</h3>
                    <button id="card" onclick="removeCart(${e.id})">Remove Cart ${e.cartNqt}</button>
                </div>
            </div>
        </div>`;
    });
    if (data.length === 0) {
        document.getElementById("buy_btn").disabled = true;
        document.getElementById("buy_btn").style.background = "white";
    }
    ans.innerHTML = app;
    localStorage.setItem("price", price);
}