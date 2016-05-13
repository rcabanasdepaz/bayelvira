// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x9_1 x9_2 x9_3);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x11_1 x11_2 x11_3);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x12_1 x12_2);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x13_1 x13_2);
}

node X14(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x14_1 x14_2 x14_3);
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
pos_x =3;
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
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
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
pos_x =6;
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
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x4_1 x4_2 x4_3);
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

link D1 X3;

link D1 X5;

link D2 V0;

link D2 X1;

link D2 X2;

link D2 X3;

link D2 X4;

link X1 X2;

link X10 D1;

link X10 X5;

link X11 D1;

link X11 X10;

link X12 D1;

link X12 X10;

link X12 X11;

link X13 D1;

link X13 X14;

link X13 X5;

link X14 D1;

link X14 X10;

link X14 X11;

link X14 X6;

link X3 X2;

link X4 X2;

link X5 D2;

link X5 X8;

link X6 D2;

link X7 D2;

link X7 X5;

link X7 X8;

link X8 D2;

link X8 X6;

link X9 D1;

link X9 X11;

link X9 X13;

link X9 X8;

//Network Relationships: 

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6834669306383172 0.3165330693616828 0.0 );
}

relation X10 X12 X14 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 1.0 0.4213210896936209 0.0 0.0 1.0 0.7209165370331354 0.2916923464420099 0.0 0.0 0.07644819635264062 0.6724114058289441 0.3148823113799861 1.0 0.017943040749318392 0.0 1.0 1.0 1.0 0.0 0.5786789103063792 1.0 1.0 0.0 0.2790834629668646 0.7083076535579901 1.0 1.0 0.9235518036473593 0.3275885941710559 0.685117688620014 0.0 0.9820569592506817 1.0 0.0 );
}

relation X11 X14 X9 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 1.0 0.3824118098381157 0.0 0.0 1.0 0.21236971459887102 0.0 0.5997362158295598 0.0 0.958426549587519 0.1689018906914945 0.0 0.7719881305368851 0.0 0.0 0.13556770318862674 1.0 0.21520780557979588 0.0 0.0 0.0 0.19354374921457604 0.0 0.787630285401129 0.23199492421159051 0.321973947711859 0.31667481263886443 0.0 0.7843588921063276 0.0 0.09877267367032334 0.5003301421741285 0.0 0.806907335242834 0.0 0.7847921944202041 0.0 0.6175881901618843 1.0 0.8064562507854239 0.0 0.0 0.7680050757884095 0.07828983645858116 0.6833251873611355 0.041573450412480985 0.046739217202177955 1.0 0.12923919579279147 0.4996698578258714 1.0 0.05752496156853924 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 );
}

relation X13 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7380262055905814 0.3598224109313417 0.7390862526742975 0.2619737944094186 0.6401775890686583 0.26091374732570255 );
}

relation X14 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4832836221978333 0.998790321849746 0.242146099335025 0.0 0.2745702784671417 0.0012096781502539784 );
}

relation X5 X10 X13 X7 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.873576124431488 1.0 1.0 1.0 0.5923843715801175 1.0 1.0 0.6886593133403132 0.11776085331677193 0.14799913558681055 0.9337319191050385 0.0 1.0 0.2072118197092955 0.5088732636972935 0.3390463113563429 1.0 0.0 1.0 1.0 0.0 1.0 0.270597145297698 0.9328024402927755 0.126423875568512 0.0 0.0 0.0 0.4076156284198825 0.0 0.0 0.31134068665968684 0.8822391466832281 0.8520008644131895 0.0662680808949615 1.0 0.0 0.7927881802907045 0.49112673630270653 0.6609536886436572 0.0 1.0 0.0 0.0 1.0 0.0 0.729402854702302 0.06719755970722445 );
}

relation X6 X8 X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2993411993757748 0.0 1.0 0.9746727834084364 1.0 0.608073819787403 0.7006588006242253 0.0 0.0 0.025327216591563645 0.0 0.391926180212597 );
}

relation X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5900197691252183 0.40998023087478186 );
}

relation X8 X7 X9 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.07019084964399147 0.6396916196313766 0.0 1.0 1.0 0.0 0.0 0.0 0.08078890192289494 0.40058588762621755 0.8650516052490249 1.0 0.9298091503560085 0.3603083803686235 1.0 0.0 0.0 0.0 0.0 0.0 0.9192110980771051 0.5994141123737824 0.134948394750975 );
}

relation X1 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.16723923591162815 0.0 0.4134928273249225 0.0 0.4192679367634495 0.0 );
}

relation X2 X1 X3 X4 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.10465121768860139 0.9297533186977435 0.5728531932318653 0.37328108114127573 1.0 0.0 1.0 0.198669285315907 0.0 0.0 1.0 0.6363810278292912 0.9492291911500098 0.30979340577954056 0.0 0.34243506534638607 0.4519721684961608 0.2443045765526039 0.0 0.02853284244346645 1.0 0.17569292400686384 0.3129458761674104 0.23115628819562226 0.7409245601671418 0.4774094713629311 0.901690899875236 1.0 1.0 0.3198636660781105 1.0 0.2186259746869287 0.0 0.3846793382785943 0.46440536027428847 0.3108875742138973 0.8953487823113986 0.07024668130225653 0.4271468067681347 0.6267189188587242 0.0 1.0 0.0 0.8013307146840931 0.0 1.0 0.0 0.3636189721707088 0.050770808849990225 0.6902065942204595 1.0 0.657564934653614 0.5480278315038393 0.7556954234473962 0.0 0.9714671575565335 0.0 0.8243070759931361 0.6870541238325897 0.7688437118043778 0.25907543983285825 0.5225905286370689 0.098309100124764 0.0 0.0 0.6801363339218894 0.0 0.7813740253130713 1.0 0.6153206617214056 0.5355946397257116 0.6891124257861028 );
}

relation X3 D2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.8348116477381603 0.0 0.28587880534576565 0.123183100229513 0.9687591839482794 0.0 0.16518835226183964 1.0 0.7141211946542344 0.876816899770487 0.03124081605172053 );
}

relation X4 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7460522016734668 0.0 0.25394779832653336 0.0 0.0 0.0 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (0.0 177.1316080055307 25.393762797586838 106.36061745830321 116.08340943235565 197.05026409307203 );
}

}
