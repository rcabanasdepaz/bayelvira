// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x8_1 x8_2 x8_3);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x11_1 x11_2 x11_3);
}

node D1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =2;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (d1_1 d1_2 d1_3);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x5_1 x5_2 x5_3);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node D2(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =5;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (d2_1 d2_2);
}

node X1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x1_1 x1_2 x1_3);
}

node X2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x2_1 x2_2);
}

node X3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
}

node V0(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =3;
pos_y =5;
relevance = 7.0;
purpose = "";
min = 0;
max = 1;
precision = 2;
}

// Links of the associated graph:

link D1 D2;

link D1 V0;

link D1 X1;

link D1 X3;

link D1 X4;

link D1 X6;

link D2 V0;

link D2 X3;

link X1 X3;

link X10 D1;

link X10 X11;

link X11 D1;

link X11 V0;

link X11 X8;

link X2 V0;

link X2 X1;

link X4 D2;

link X4 V0;

link X5 D2;

link X5 X10;

link X5 X7;

link X6 D2;

link X6 X2;

link X6 X4;

link X7 D2;

link X7 X8;

link X8 D1;

link X8 X2;

link X9 D1;

link X9 X11;

//Network Relationships: 

relation X8 X7 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4277612681875303 0.0 0.10090833233012078 0.9137259939761869 0.05788342437120537 0.36202066522601745 0.2364429582927806 0.7073220850378886 0.6350405672823313 0.0862740060238131 0.3255658529158413 0.0 0.33579577351968914 0.2926779149621113 0.26405110038754787 0.0 0.6165507227129533 0.6379793347739826 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9671507241098022 0.03284927589019773 );
}

relation X10 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 0.0 0.0 1.0 0.0 );
}

relation X11 X9 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.526254261487482 0.0 0.0 0.675537758307697 0.05311491508190484 1.0 0.054546870310544206 0.0544171806073341 0.42063082343061303 0.0 0.9454531296894558 0.27004506108496884 );
}

relation X4 X6 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3047591326122723 0.5341083869485801 1.0 0.0 0.11849799153170947 0.5534556481788824 0.6952408673877277 0.46589161305141996 0.0 1.0 0.8815020084682905 0.44654435182111774 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.7716481400183798 0.2283518599816203 );
}

relation X6 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8963316766325737 0.0010735881305965816 0.7713900163981323 0.10366832336742623 0.9989264118694035 0.22860998360186763 );
}

relation X7 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 1.0 1.0 0.0 0.0 0.0 );
}

relation X1 X2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5104595153992041 0.0869810925974693 1.0 0.0 0.0 0.27340589782765795 0.10159051691332681 0.6896450622988531 0.0 0.6274765423544426 1.0 0.21720433074126844 0.3879499676874691 0.22337384510367758 0.0 0.3725234576455573 0.0 0.5093897714310737 );
}

relation X2 X8 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.7442379348082154 0.5555090130555163 0.30991505430871713 0.45510174576118445 0.0 0.0 0.2557620651917846 0.44449098694448375 0.690084945691283 0.5448982542388155 1.0 );
}

relation X3 D2 X1 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2600798013180963 0.9866585246467577 1.0 0.4682658593111184 0.5896803380432594 0.33086218325740996 0.34449395145428785 1.0 0.0 0.3105794934327962 1.0 0.0 0.4346399617765984 0.0 0.13121400279032702 0.0 0.0 0.12330488566231515 0.7399201986819036 0.013341475353242262 0.0 0.5317341406888816 0.41031966195674063 0.6691378167425901 0.6555060485457123 0.0 1.0 0.6894205065672039 0.0 0.0 0.5653600382234016 1.0 0.868785997209673 1.0 1.0 0.8766951143376849 );
}

relation V0 D1 D2 X11 X2 X4 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (213.94510756633406 206.63845602562265 246.9369388703821 0.0 0.0 0.0 308.4499688471605 0.0 207.67586009494664 200.36920855423523 240.66769139899466 233.36103985828328 201.03472026465735 193.72806872394597 234.02655156870537 0.0 0.0 255.24109870072436 295.5395815454838 288.2329300047724 194.76547279326994 0.0 227.75730409731796 220.45065255660654 169.2290700382737 161.92241849756232 202.22090134232172 194.9142498016103 230.74210001505213 0.0 263.7339313191002 256.42727977838877 162.9598225668863 155.6531710261749 0.0 0.0 0.0 149.01203119588558 189.31051404064502 182.00386249993363 217.8317127133754 0.0 250.82354401742344 243.51689247671203 150.04943526520958 0.0 183.0412665692576 175.73461502854622 214.44009566482026 207.13344412410885 0.0 240.1252754281569 275.95312564159866 268.64647410088725 308.9449569456467 301.6383054049353 0.0 200.86419665272143 241.16267949748084 233.85602795676942 201.52970836314353 194.2230568224321 234.52153966719155 0.0 263.0427383399219 0.0 0.0 288.72791810325856 0.0 187.9538093510447 0.0 220.94564065509275 );
}

}
