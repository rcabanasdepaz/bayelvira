// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =2;
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

node D1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =2;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (d1_1 d1_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x11_1 x11_2);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x12_1 x12_2);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x13_1 x13_2);
}

node X14(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x14_1 x14_2);
}

node X15(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x15_1 x15_2);
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
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x1_1 x1_2);
}

node X2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x2_1 x2_2);
}

node X3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x5_1 x5_2);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node V0(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =1;
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

link D1 X11;

link D1 X12;

link D1 X13;

link D1 X14;

link D1 X3;

link D1 X6;

link D2 V0;

link D2 X3;

link X1 X12;

link X10 D1;

link X10 X7;

link X11 D2;

link X11 V0;

link X11 X14;

link X12 D2;

link X12 V0;

link X13 D2;

link X13 X11;

link X14 D2;

link X14 X12;

link X14 X15;

link X14 X5;

link X15 D2;

link X15 V0;

link X15 X1;

link X2 X15;

link X4 X3;

link X4 X9;

link X5 X1;

link X6 X15;

link X6 X2;

link X7 D1;

link X7 X11;

link X8 D1;

link X8 X11;

link X9 D1;

link X9 X11;

//Network Relationships: 

relation X7 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4533630486093982 0.09510123156670773 0.5466369513906019 0.9048987684332923 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.45098131239617895 0.549018687603821 );
}

relation X9 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.864697902968556 0.48986776199752824 0.1353020970314441 0.5101322380024718 );
}

relation X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5426027944789616 0.4573972055210384 );
}

relation X11 D1 X13 X7 X8 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9100097132454505 0.5019211527121663 0.976431274605338 0.4026368951749405 0.32050858185113 0.531629876917147 0.05213969496158885 0.04843258211866129 0.9690244540657145 0.8232609366404653 0.6608069368222246 0.5155424234507574 0.48232833419204 0.5692154433926988 0.2813547177950727 0.6404980406106012 0.2172837141691946 0.48285154252620227 0.5768830270953249 0.6248876993829867 0.648874294282434 0.20205858490423903 0.26124833443112183 0.11330036094353221 0.1482555061724196 0.5683269719956263 0.6254472028344461 0.5861298307674506 0.7668741773644532 0.2387321179389555 0.8263579904465763 0.3521184959677681 0.08999028675454951 0.4980788472878336 0.023568725394661958 0.5973631048250596 0.67949141814887 0.468370123082853 0.9478603050384111 0.9515674178813388 0.030975545934285593 0.17673906335953465 0.33919306317777537 0.4844575765492425 0.51767166580796 0.4307845566073011 0.7186452822049273 0.3595019593893987 0.7827162858308054 0.5171484574737978 0.423116972904675 0.37511230061701323 0.351125705717566 0.797941415095761 0.7387516655688782 0.8866996390564678 0.8517444938275804 0.4316730280043736 0.374552797165554 0.41387016923254943 0.23312582263554676 0.7612678820610445 0.17364200955342363 0.647881504032232 );
}

relation X12 D1 X1 X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5440362807692044 0.39451476507910777 0.5363667236276211 0.6698817888518147 0.169051976319726 0.5258534156797305 0.5797728439861878 0.6365956976944283 0.45596371923079554 0.6054852349208921 0.46363327637237894 0.33011821114818535 0.8309480236802741 0.47414658432026946 0.4202271560138122 0.3634043023055717 );
}

relation X13 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7654509734083528 0.6518601682761169 0.23454902659164717 0.3481398317238831 );
}

relation X14 D1 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7504769495843951 0.5984479259971107 0.8834733529399262 0.9884640987313613 0.2495230504156049 0.4015520740028892 0.11652664706007375 0.011535901268638632 );
}

relation X15 X14 X2 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.1372639454813852 0.40254059532246195 0.46103268692196336 0.9753560817617314 0.6840239687239408 0.6240583334782628 0.29598512068918 0.7424284677187182 0.8627360545186148 0.5974594046775381 0.5389673130780366 0.024643918238268694 0.31597603127605917 0.3759416665217372 0.7040148793108201 0.2575715322812819 );
}

relation X1 D1 X15 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.591130382662382 0.5548721329031101 0.5552803104753656 0.6769782973798342 0.06019840234543731 0.48221721064920564 0.29957645561807417 0.9890012411967837 0.4088696173376179 0.44512786709688995 0.44471968952463425 0.3230217026201658 0.9398015976545626 0.5177827893507942 0.7004235443819258 0.010998758803216296 );
}

relation X2 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9791152051336909 0.5386090259620738 0.020884794866309148 0.46139097403792606 );
}

relation X3 D1 D2 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.15456677435185964 0.41047789139043367 0.5355350909621895 0.4079264890049175 0.2868644372736428 0.359779239893669 0.127239655288428 0.6130876093265858 0.8454332256481403 0.5895221086095663 0.4644649090378105 0.5920735109950825 0.7131355627263573 0.6402207601063309 0.8727603447115719 0.38691239067341426 );
}

relation X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8290031059794061 0.17099689402059393 );
}

relation X5 X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8877279880824397 0.5870336206859689 0.11227201191756037 0.4129663793140311 );
}

relation X6 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9360822847420562 0.2262959170831847 0.06391771525794368 0.7737040829168153 );
}

relation V0 D1 D2 X11 X12 X15 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (313.3890025627635 220.2930411213599 340.4296668885262 247.33370544712253 300.18124050390725 207.08527906250364 327.22190482966994 234.12594338826628 273.4534870883472 180.35752564694357 300.49415141410987 207.3981899727062 260.245725029491 167.14976358808735 287.2863893552536 194.19042791385 343.86655762561014 250.7705961842065 370.9072219513728 277.81126050996915 330.6587955667539 237.56283412535026 357.69945989251653 264.6034984511129 303.9310421511939 210.83508070979022 330.97170647695646 237.87574503555285 290.72328009233763 197.62731865093397 317.7639444181002 224.6679829766966 );
}

}
