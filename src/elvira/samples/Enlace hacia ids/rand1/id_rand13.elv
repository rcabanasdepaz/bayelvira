// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x5_1 x5_2);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
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

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =3;
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

link D1 X10;

link D1 X2;

link D1 X8;

link D2 V0;

link D2 X1;

link D2 X2;

link D2 X3;

link X1 X2;

link X1 X3;

link X10 D2;

link X4 D1;

link X4 X3;

link X5 D1;

link X5 X1;

link X6 D1;

link X6 X4;

link X7 D2;

link X7 X4;

link X8 D2;

link X8 X10;

link X9 D2;

link X9 X10;

link X9 X4;

//Network Relationships: 

relation X4 X6 X7 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7139338496156484 0.8648228365640527 0.010223794832487932 0.1946029378752201 0.28295291536251727 0.8056369752151804 0.4754029912284927 0.6037284021876446 0.2860661503843516 0.13517716343594727 0.989776205167512 0.8053970621247799 0.7170470846374828 0.19436302478481948 0.5245970087715074 0.3962715978123553 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6511984890849912 0.34880151091500883 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6067559711263123 0.3932440288736877 );
}

relation X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.38693709283426786 0.6130629071657321 );
}

relation X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7840176485123398 0.35834971953409217 0.2159823514876603 0.6416502804659078 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5178155920107407 0.4821844079892594 );
}

relation X10 D1 X8 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5848647401403362 0.06664743360283906 0.03395913687799226 0.8376255723952009 0.6423282646946326 0.8335522773180805 0.44509239275301277 0.018879543908348877 0.4151352598596639 0.9333525663971609 0.9660408631220078 0.16237442760479912 0.35767173530536756 0.16644772268191957 0.5549076072469873 0.9811204560916511 );
}

relation X1 D2 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.04790005485417615 0.5612574143817218 0.5671010183755671 0.7899748081744782 0.9520999451458239 0.43874258561827806 0.4328989816244329 0.21002519182552185 );
}

relation X2 D1 D2 X1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4712534656719592 0.6178258541770576 0.9699806548362288 0.8555514918415079 0.2641915960856294 0.5235897635957142 0.6666948439842981 0.5585631272014203 0.5287465343280409 0.38217414582294235 0.03001934516377124 0.14444850815849208 0.7358084039143705 0.47641023640428576 0.33330515601570193 0.44143687279857974 );
}

relation X3 D2 X1 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9608503094972293 0.3044836973872535 0.5354727138920334 0.08628663194062343 0.5800416821438227 0.32354418787783507 0.32565728455578935 0.28778954955898534 0.03914969050277077 0.6955163026127463 0.4645272861079665 0.9137133680593766 0.4199583178561773 0.6764558121221649 0.6743427154442106 0.7122104504410147 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (150.41706200977592 110.48154653535961 180.89461707262254 140.95910159820625 );
}

}
