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

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x11_1 x11_2);
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

node X16(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x16_1 x16_2);
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
pos_x =2;
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

link D1 X12;

link D1 X13;

link D1 X15;

link D1 X3;

link D2 V0;

link D2 X1;

link D2 X3;

link D2 X5;

link X10 D1;

link X10 X12;

link X11 D1;

link X11 X10;

link X12 D2;

link X12 V0;

link X12 X13;

link X13 D2;

link X13 V0;

link X14 D2;

link X14 X15;

link X15 D2;

link X15 X12;

link X15 X16;

link X16 D2;

link X16 V0;

link X16 X2;

link X2 X12;

link X3 X4;

link X3 X5;

link X5 X4;

link X6 X2;

link X6 X5;

link X7 D1;

link X7 X11;

link X8 D1;

link X8 X7;

link X9 D1;

link X9 X10;

//Network Relationships: 

relation X7 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4215759923991563 0.23887644427600288 0.5784240076008438 0.7611235557239971 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5134865982152885 0.4865134017847114 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.33828480124408417 0.6617151987559158 );
}

relation X10 X11 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.41475774561857043 0.1256372432557639 0.47583250685459794 0.5307600583338551 0.5852422543814295 0.8743627567442361 0.524167493145402 0.46923994166614497 );
}

relation X11 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.563986809143537 0.3992248063899571 0.436013190856463 0.600775193610043 );
}

relation X12 D1 X10 X15 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7890678771271203 0.4709994577156858 0.006978957035660901 0.25180485433687816 0.6641501109055574 0.12270322327777848 0.7453677358176226 0.7189943899244967 0.6595585863920002 0.3266607494684843 0.37798183095366045 0.6876701188894622 0.18021375490273728 0.7963869701686994 0.49761621911831844 0.44395471732155173 0.21093212287287968 0.5290005422843141 0.993021042964339 0.7481951456631218 0.33584988909444247 0.8772967767222215 0.2546322641823774 0.2810056100755032 0.34044141360799973 0.6733392505315158 0.6220181690463394 0.31232988111053783 0.8197862450972627 0.2036130298313006 0.5023837808816817 0.5560452826784483 );
}

relation X13 D1 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2876099513578875 0.185662205628142 0.6091980871364703 0.7068241140613204 0.7123900486421124 0.8143377943718579 0.39080191286352983 0.29317588593867955 );
}

relation X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8170690858710418 0.18293091412895823 );
}

relation X15 D1 X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.692250424785362 0.638279467504349 0.43896889336661155 0.6032036547760535 0.30774957521463786 0.361720532495651 0.5610311066333884 0.39679634522394647 );
}

relation X16 X15 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6884113680173838 0.0864114166721891 0.3115886319826163 0.9135885833278109 );
}

relation X1 D1 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.06137568755257785 0.5156449669942901 0.7500407244808118 0.2151677073700232 0.9386243124474222 0.4843550330057099 0.24995927551918826 0.7848322926299769 );
}

relation X2 X16 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.45488982183712257 0.9028755753418293 0.9893859638740077 3.824844369333984E-4 0.5451101781628774 0.09712442465817073 0.010614036125992344 0.9996175155630667 );
}

relation X3 D1 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6008827590564996 0.4721805330457549 0.23093941698304105 0.8358612866285734 0.3991172409435005 0.5278194669542452 0.7690605830169589 0.16413871337142671 );
}

relation X4 X3 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.46927891453209575 0.029361474878384284 0.11061091402308486 0.48179901569159217 0.5307210854679043 0.9706385251216156 0.8893890859769151 0.5182009843084078 );
}

relation X5 D2 X3 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7251322010163918 0.7608447802354272 0.9721068241494979 0.30742743049411286 0.9067142027247485 0.40601660819695734 0.7101455571818179 0.8918582722557404 0.27486779898360825 0.23915521976457285 0.02789317585050216 0.6925725695058871 0.09328579727525148 0.5939833918030427 0.2898544428181822 0.10814172774425952 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5481532967982689 0.45184670320173104 );
}

relation V0 D1 D2 X12 X13 X16 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (313.3890025627635 220.2930411213599 340.4296668885262 247.33370544712253 300.18124050390725 207.08527906250364 327.22190482966994 234.12594338826628 273.4534870883472 180.35752564694357 300.49415141410987 207.3981899727062 260.245725029491 167.14976358808735 287.2863893552536 194.19042791385 343.86655762561014 250.7705961842065 370.9072219513728 277.81126050996915 330.6587955667539 237.56283412535026 357.69945989251653 264.6034984511129 303.9310421511939 210.83508070979022 330.97170647695646 237.87574503555285 290.72328009233763 197.62731865093397 317.7639444181002 224.6679829766966 );
}

}
