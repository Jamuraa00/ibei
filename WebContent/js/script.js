//D3
var h = 500;
var w = 1050;
var padding = 40;

var legendSize = 100;
var datavar;
var nested_data;
var timeFormat = d3.time.format('%Y-%m-%d');

var x = d3.scale.ordinal()
	.domain(["5 15","6 15","7 15", "8 15", "9 15", "10 15","11 15","0 16","1 16", "2 16","3 16","4 16","5 16"])
	.rangeRoundBands([padding, w-padding - legendSize  - 10], .1);
	
var y = d3.scale.linear()
	.range([h - padding,padding]);

var xAxis = d3.svg.axis()
	.scale(x)
	.orient('bottom')
	.tickFormat(function(d) {
		n = d.split(" ");
		n0 = +n[0] + 1;
		d = n0 + " " + n[1]
		f = d3.time.format('%m %y');
		s = f.parse(d);
		f = d3.time.format('%b \'%y');
		return f(s);
	});

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
    .text("Months");

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

d3.csv('data/data.csv', function(data) {
	datavar = data;
	data.forEach(function(d) {
		d.date = timeFormat.parse(d['date']);
		d.month = d.date.getMonth() + " " + d.date.getFullYear();
		d.name = d['name'];
		d.cat = d['category'];
		d.quantity = +d['quantity'];
		d.price = +d['price'];
		d.color = d['cat_color'];
	});
	
	nested_data = d3.nest()
		.key(function(d) {return d.month})
		.rollup(function(d) {
			return d3.sum(d, function(g) {return g.quantity; })
		})
		.entries(data)
		
	y.domain([0, d3.max(nested_data, function(d) { return d.values; })]);
	svg.select('g#xaxis').transition().duration(1000).call(xAxis);

	svg.select('g#yaxis').transition().duration(1000).call(yAxis);
	
	svg.selectAll(".bar")
    	.data(nested_data)
    	.enter().append("rect")
    		.attr("class", "bar")
    		.attr("x", function(d) { return x(d.key); })
			.attr("width", x.rangeBand())
			.attr("y", function(d) { return y(d.values); })
			.attr("height", function(d) { return h - y(d.values) - padding; })
			.attr('fill','#4682B4');

function filter(data,category) {
	 var color;
	 var filtered = data
	if(category != "Overall") {
		filtered = data.filter(function(d) {
			return d.category == category;
		});
		color = filtered[0].cat_color;
	}
	else
		color = '#4682B4'
	nested_data = d3.nest()
	.key(function(d) {return d.month})
	.rollup(function(d) {
		return d3.sum(d, function(g) {return g.quantity; })
	})
	.entries(filtered);
	
	y.domain([0, d3.max(nested_data, function(d) { return d.values; })]);
	svg.select('g#xaxis').transition().duration(1000).call(xAxis);

	svg.select('g#yaxis').transition().duration(1000).call(yAxis);
	
	svg.selectAll(".bar")
    	.data(nested_data)
    	.enter().append("rect")
    		.attr("class", "bar")
    		.attr("x", function(d) { return x(d.key); })
			.attr("width", x.rangeBand())
			.attr("y", function(d) { return y(d.values); })
			.attr("height", function(d) { return h - y(d.values) - padding; })
			//.attr('fill','black');
	
	svg.selectAll(".bar")
	.data(nested_data)
	.transition()
	.duration(1000)
	.attr("y", function(d) { return y(d.values); })
	.attr("height", function(d) { return h - y(d.values) - padding; })
	.attr('fill',color);
	
	svg.selectAll('.line')
	.data(nested_data)
	.exit()
	.transition()
	.duration(500)
	.attr("height", function(d) { return h - padding; })
	.remove()
}

//JQUERY
$("#ovrl").toggleClass("btn-default btn-primary");

$('#button-row > .btn').click(function() {
	$('#button-row > .btn').each(function() {
		$(this).toggleClass("btn-default",true)
		$(this).toggleClass("btn-primary",false)
	});
	$(this).toggleClass("btn-default",false)
	$(this).toggleClass("btn-primary",true)
	
	filter(datavar, $(this).text())
});
});

