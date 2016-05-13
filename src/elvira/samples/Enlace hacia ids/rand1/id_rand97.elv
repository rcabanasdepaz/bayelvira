// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x11_1 x11_2);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x12_1 x12_2);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x13_1 x13_2);
}

node X14(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x14_1 x14_2);
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

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x5_1 x5_2);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
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

link D1 X10;

link D1 X3;

link D1 X7;

link D1 X8;

link D2 V0;

link D2 X3;

link D2 X4;

link X1 X10;

link X10 D2;

link X10 X9;

link X11 D1;

link X11 X13;

link X12 D1;

link X12 X11;

link X13 D1;

link X13 X14;

link X14 D1;

link X14 X10;

link X2 X13;

link X2 X3;

link X2 X4;

link X5 D2;

link X5 X7;

link X5 X9;

link X6 D2;

link X6 X11;

link X7 D2;

link X7 X10;

link X8 D2;

link X8 X1;

link X8 X5;

link X9 D2;

link X9 X3;

//Network Relationships: 

relation X11 X12 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7888414244196769 0.2385057348973549 0.356104518209147 0.5594477239084529 0.21115857558032322 0.7614942651026451 0.643895481790853 0.44055227609154723 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5047321243617812 0.4952678756382187 );
}

relation X13 X11 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7425273343954831 0.7654988539407709 0.6794085855441849 0.8267794609981814 0.257472665604517 0.23450114605922906 0.32059141445581507 0.17322053900181858 );
}

relation X14 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.46246573247676853 0.4265380002609121 0.5375342675232314 0.573461999739088 );
}

relation X5 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9473871116115448 0.7878267575396831 0.05261288838845519 0.21217324246031694 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7133922109482184 0.28660778905178175 );
}

relation X7 D1 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4705991903748914 0.23390660292611515 0.342122950674512 0.8560852118516497 0.5294008096251086 0.7660933970738849 0.6578770493254881 0.1439147881483503 );
}

relation X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7071349805367716 0.7661994683505515 0.29286501946322835 0.23380053164944845 );
}

relation X9 X10 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7662623099863567 0.14553018749898128 0.24981728420983165 0.6415553294994826 0.23373769001364322 0.8544698125010187 0.7501827157901684 0.3584446705005174 );
}

relation X10 D1 X1 X14 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7935435700629545 0.47669805118570185 0.28567405458449885 0.6050095563550705 0.5971135661659227 0.019050246035088533 0.2168389243278488 0.4029276426151392 0.6216540255574391 0.7991913848154617 0.518859105361936 0.5044341486124829 0.5264074226641983 0.3976482989500203 0.5155958975732302 0.42689637801575275 0.2064564299370455 0.5233019488142983 0.7143259454155012 0.3949904436449297 0.40288643383407735 0.9809497539649115 0.7831610756721513 0.5970723573848609 0.3783459744425609 0.20080861518453835 0.481140894638064 0.4955658513875172 0.4735925773358017 0.6023517010499798 0.48440410242676973 0.5731036219842472 );
}

relation X1 D1 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3940685512674256 0.657761889995499 0.2394434996688883 0.4089398919667775 0.6059314487325744 0.34223811000450105 0.7605565003311117 0.5910601080332225 );
}

relation X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5733598261536577 0.4266401738463424 );
}

relation X3 D1 D2 X2 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8731728412428955 0.46865999031286343 0.44630575788908183 0.7084680448451295 0.7028636539079952 0.45007710423745734 0.3185984176211419 0.4796853914747581 0.7445303039551736 0.27060060934077906 0.2265057291991735 0.44898900045668805 0.3291365906302734 0.6020247899611634 0.34591170202405924 0.5807126481409198 0.1268271587571045 0.5313400096871366 0.5536942421109182 0.29153195515487046 0.29713634609200484 0.5499228957625426 0.6814015823788582 0.5203146085252418 0.2554696960448263 0.7293993906592209 0.7734942708008266 0.5510109995433119 0.6708634093697267 0.3979752100388366 0.6540882979759407 0.4192873518590803 );
}

relation X4 D2 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6587248016118458 0.6486714055610977 0.549742473174386 0.07223118885672565 0.34127519838815423 0.35132859443890224 0.45025752682561404 0.9277688111432743 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (150.41706200977592 110.48154653535961 180.89461707262254 140.95910159820625 );
}

}
