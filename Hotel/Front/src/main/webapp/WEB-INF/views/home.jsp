<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="images" value="/resources/images"/>
<div class="container-fluid">
<div id="myCarousel" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
                <li data-target="#myCarousel" data-slide-to="3"></li>
            </ol>
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img class="first-slide home-image" src="${images}/Hotel.jpg" alt="first slide" height="600px" width="1000px">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>WELCOME TO THE Booking</h1>
                            <p >Order Now For Your Amazing New Arrivals</p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img class="second-slide home-image" src="${images}/hotel2.jpg" alt="second slide" height="600px" width="1000px">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>The Booking Way</h1>
                            <p>Range your Booking</p>
                            <p>Make Up To Your Style!!</p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img class="third-slide home-image" src="${images}/hotel3.jpg" alt="third slide" height="600px" width="1000px">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>The Booking on</h1>
                            <p>online booking can make your life more easier</p>
                            <p>Transform It In Your Way!!</p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img class="fourth-slide home-image" src="${images}/hotel4.jpg" alt="fourth slide" height="600px" width="1000px">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>The Dashing view</h1>
                            <p>Variations of Ranges</p>
                            <p>Feel The Hotness In You!!</p>
                        </div>
                    </div>
                </div>
                
               
            </div>
            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" ></span>
                <span class="sr-only">Next</span>
            </a>
        </div><!-- /.carousel -->

</div>