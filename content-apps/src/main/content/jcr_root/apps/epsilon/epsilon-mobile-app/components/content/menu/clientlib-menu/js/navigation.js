(function(){
 // navigation component specific code here…

 var controller = new slidebars();
 	 controller.init();

 	$( '.toggle-slidebar' ).on( 'click', function ( event ) {

        event.stopPropagation();
        controller.toggle( 'left-nav' );
    });

	$('.mainNavigation> ul> li> a').on('click', function(){
			$('.mainNavigation li').removeClass('active');
			$(this).parent().addClass('active')	;

			if($(window).width() < 990){
				$('.mainNavigation').addClass('mobileNav');
				
				if($('.subItem').is(":visible")){
					$('.navigation').addClass('mobilePosition');
				}
			}

		})

		$('.mainNavigation ul li').on('click', '.closebtn', function(){
			$('.mainNavigation li').removeClass('active');
			$('.mainNavigation').removeClass('mobileNav');
			
			if($(window).width() < 990){
				$('.navigation').toggleClass('mobilePosition');
			}

		})


		$('.two-level li').on('click', 'a', function(){
			if($(window).width() < 990){
				$('.two-level li').removeClass('expand');
				$(this).parent().addClass('expand');
			}
		})


		$('.icon-Whoweare').closest('li').addClass('one-column')
		
})();