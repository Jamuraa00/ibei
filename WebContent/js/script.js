var h = 500;
var w = 1050;
var padding = 40;

var legendSize = 100;

//var timeFormat = d3.time.format('%b \'%y');
var timeFormat = d3.time.format('%Y-%m-%d');
var datemin = new Date();
datemin.setMonth(5);
datemin.setHours(0);
var x = d3.time.scale()
	.domain([datemin,new Date().setHours(0)])
	.range([padding,w-padding - legendSize  - 10]);
	

var y = d3.scale.linear()
	.domain([0,200])
	.range([h - padding,padding]);

var xAxis = d3.svg.axis()
	.scale(x)
	.orient('bottom')
	.ticks(12)
	.tickFormat(d3.time.format('%d/%m'));

var yAxis = d3.svg.axis()
	.scale(y)
	.orient('left');

var svg = d3.select('#chart')
		.append('svg')
		.attr('width',w)
		.attr('height',h);

svg.append('g')
	.attr('class','axis')
	.attr('id','xaxis')
	.attr('transform','translate(0,'+ (h - padding) + ')')
	.call(xAxis);

svg.append("text")
    .attr("class", "x label")
    .attr("text-anchor", "end")
    .attr("x", w - padding - legendSize)
    .attr("y", h - 10)
    .text("Days");

svg.append('g')
	.attr('class','axis')
	.attr('id','yaxis')
	.attr('transform','translate('+padding+',0)')
	.call(yAxis);

svg.append("text")
    .attr("class", "y label")
    .attr("text-anchor", "end")
    .attr("x", -padding)
    .attr("y", padding +10)
    .attr("dy", ".75em")
    .attr("transform", "rotate(-90)")
    .text("Units sold");

var line = d3.svg.line()
	.x(function(d) {return x(d.date);})
	.y(function(d) {return y(d.quantity);})
	.interpolate('basis');


var airfrance=[], alitalia=[], ba=[];
var company = new Set();

d3.csv('data/sales.csv', function(data) {
	data.forEach(function(d) {
		d.date = timeFormat.parse(d['date']);
		d.name = d['name'];
		d.cat = d['category'];
		d.quantity = +d['quantity'];
		d.price = +d['price'];
		d.color = d['cat_color'];
		
			
		/*else if(d.companyCode == 'BA')
			ba.push(d);
		else if(d.companyCode == 'AF')
			airfrance.push(d);*/
	});

	svg.selectAll('.line')
		.data([data])
		.enter()
		.append('path')
		.attr('class', 'line')
		.attr('fill','none')
		.attr('stroke','white')
		.attr('d', line);

	svg.selectAll('rect.legend')
		.data([data])
		.enter()
		.append('rect')
		.attr('class','legend')
		.attr('x',w - padding - legendSize + 5)
		.attr('y',function(d,i) {return h})
		.attr('width', 10)
    	.attr('height', 10)
		.attr('fill',function(d) {return d[0].color})

	svg.selectAll('text.legend')
		.data([data])
		.enter()
		.append('text')
		.attr('class','legend')
	    .attr('x', w - padding - legendSize + 20)
	    .attr('y', function(d, i){ return h/2 + i*20 + 9;})
	    .text(function(d){ return '' });
		
	svg.selectAll('.line')
		.data([data])
		.transition()
		.duration(500)
		.attr('stroke-width',1.5)
		.attr('stroke',function(d) {return d[0].color})
		.attr('d', line);

	svg.selectAll('rect.legend')
		.data([data])
		//.transition()
		//.duration(500)
		//.attr('x',w - legendSize)
		.attr('y',function(d,i) {return h/2 + i*20})
		//.attr('width', 10)
    	//.attr('height', 10)
		.attr('fill',function(d) {return d[0].color})

	svg.selectAll('text.legend')
		.data([data])
		.transition()
		.duration(500)
	    //.attr('x', w - padding - 40 + 5)
	    //.attr('y', function(d, i){ return h/2 + i*20 + 9;})
	    .text(function(d){ return d[0].name; });

	svg.selectAll('.line')
		.data([data])
		.exit()
		.transition()
		.duration(500)
		.attr('stroke','white')
		.remove()

	svg.selectAll('rect.legend')
		.data([data])
		.exit()
		//.transition()
		//.duration(500)
		//.attr('y',0)
		//.attr('width', 0)
    	//.attr('height', 0)
		.remove()

	svg.selectAll('text.legend')
		.data([data])
	    .exit()
	    //.transition()
		//.duration(500)
	    //.attr('x', w)
	    //.attr('y', 0)
	    .text(function(d){ return ''})
		.remove()

	//data =[airfrance,alitalia,ba];

	//x.domain(d3.extent(data, function(d) {return d.date}));
});
	

	//Draw axis
//	svg.select('g#xaxis').transition().duration(1000).call(xAxis);

//	svg.select('g#yaxis').transition().duration(1000).call(yAxis);


/*$('#form input:checkbox').change(function() {
		
		if($(this).prop('checked'))
			company.add($(this).val());
		else
			company.delete($(this).val())
		
		f(data,company);
	});
});*/
function f(data,company) {
	
	var datum = data.filter(function(d) {
		return company.has(d[0].companyCode);
	});

	
	svg.selectAll('.line')
		.data(datum)
		.enter()
		.append('path')
		.attr('class', 'line')
		.attr('fill','none')
		.attr('stroke','white')
		.attr('d', line);

	svg.selectAll('rect.legend')
		.data(datum)
		.enter()
		.append('rect')
		.attr('class','legend')
		.attr('x',w - padding - legendSize + 5)
		.attr('y',function(d,i) {return h})
		.attr('width', 10)
    	.attr('height', 10)
		.attr('fill',function(d) {return d[0].color})

	svg.selectAll('text.legend')
		.data(datum)
		.enter()
		.append('text')
		.attr('class','legend')
	    .attr('x', w - padding - legendSize + 20)
	    .attr('y', function(d, i){ return h/2 + i*20 + 9;})
	    .text(function(d){ return '' });
		
	svg.selectAll('.line')
		.data(datum)
		.transition()
		.duration(500)
		.attr('stroke-width',1.5)
		.attr('stroke',function(d) {return d[0].color})
		.attr('d', line);

	svg.selectAll('rect.legend')
		.data(datum)
		//.transition()
		//.duration(500)
		//.attr('x',w - legendSize)
		.attr('y',function(d,i) {return h/2 + i*20})
		//.attr('width', 10)
    	//.attr('height', 10)
		.attr('fill',function(d) {return d[0].color})

	svg.selectAll('text.legend')
		.data(datum)
		.transition()
		.duration(500)
	    //.attr('x', w - padding - 40 + 5)
	    //.attr('y', function(d, i){ return h/2 + i*20 + 9;})
	    .text(function(d){ return d[0].companyName; });

	svg.selectAll('.line')
		.data(datum)
		.exit()
		.transition()
		.duration(500)
		.attr('stroke','white')
		.remove()

	svg.selectAll('rect.legend')
		.data(datum)
		.exit()
		//.transition()
		//.duration(500)
		//.attr('y',0)
		//.attr('width', 0)
    	//.attr('height', 0)
		.remove()

	svg.selectAll('text.legend')
		.data(datum)
	    .exit()
	    //.transition()
		//.duration(500)
	    //.attr('x', w)
	    //.attr('y', 0)
	    .text(function(d){ return ''})
		.remove()
	
	//Draw axis
	//svg.select('g#xaxis').transition().duration(500).call(xAxis);

	//svg.select('g#yaxis').transition().duration(500).call(yAxis);

	
}