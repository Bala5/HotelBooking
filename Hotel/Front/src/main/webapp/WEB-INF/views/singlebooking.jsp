<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CR"
	value="${pageContext.request.contextPath}/resources/pimages" />
<c:set var="s" value="${pageContext.request.contextPath}" />

<div class="container">


	<div class="row">
		<div class="col-sm-5 ">

			<div>
				<img src="${CR}/${product.productId}.jpg" width="300px"
					height="300px">
			</div>
		</div>
		<div class="col-sm-7">
			<div class="card-body p-5">
				<h3 class="title mb-3">${product.productName}</h3>

				<p class="price-detail-wrap">
					<span class="price h3 text-warning"> <span class="currency">&#8377</span><span
						class="num">${product.price}</span>
					</span>
				</p>
				<!-- price-detail-wrap .// -->
				<dl class="item-property">
					<dt>Description</dt>
					<dd>
						<p>${product.productDescription}</p>
					</dd>
				</dl>

				<dl class="param param-feature">
					<dt>Color</dt>
					<dd>Black and white</dd>
				</dl>
				<dl class="param param-feature">
					<dt>Delivery</dt>
					<dd>Free Shipping And COD For COIMBATORE,CHENNAI AND BANGALORE</dd>
				</dl>
				<c:if test="${product.quantity != 0 }">
					<label>Enter Quantity</label>
					<input type="number" min="1" max="5" name="upqty" id="upqty"
						class="text-center" value="1">
				</c:if>
				<p id="demo"></p>
				<br>
				<c:if test="${msg}">
					<span class="alert alert-danger">Requested quantity not
						available</span>
				</c:if>
				<br>

				<hr>

				<a href="${s}/productgrid" class="btn btn-success btn-product"><span
					class="glyphicon glyphicon-shopping-cart"></span>BACK</a>
				<c:if test="${product.quantity == 0 }">
					<span class="btn btn-primary btn-product ">Out of Stock</span>
				</c:if>
				<c:if test="${product.quantity != 0 }">

					<button type="button" name="refreshcart" id="refreshcart"
						onclick='setURL()' value="${product.productId}"
						class="btn btn-success btn-product">
						<span class="glyphicon glyphicon-shopping-cart"></span>Add to Cart
					</button>
				</c:if>

				<a href="${s}/cart/addwish/${product.productId}" class="btn button">
					<span class="glyphicon glyphicon-heart"></span>Add to Wishlist
				</a>

			</div>
		</div>
	</div>


</div>

<script>
	$('button[name="refreshcart"]').click(
			function() {
				var text;
				var count = $('#upqty');
				orgcount = count.attr('value');

				var cartid = document.getElementById("refreshcart").value;
				console.log(cartid);
				var qnty = document.getElementById("upqty").value;
				if (qnty<1 || qnty>5) {
					count.val(orgcount);
					text = "Min 1/Max 5";

				} else {
					text = "";
					var url = "http://localhost:8080/BoutiqueFront/addproduct/"
							+ cartid + "?qnty=" + qnty;
					window.location.href = url;
				}
				document.getElementById("demo").innerHTML = text;

			});
</script>


