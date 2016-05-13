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
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x11_1 x11_2 x11_3);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x12_1 x12_2);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x13_1 x13_2);
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
pos_x =5;
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

link D1 X5;

link D1 X6;

link D2 V0;

link D2 X3;

link X1 V0;

link X1 X7;

link X10 D1;

link X10 X11;

link X11 D1;

link X11 X9;

link X12 D1;

link X12 X10;

link X12 X13;

link X12 X2;

link X12 X9;

link X13 X11;

link X13 X3;

link X2 X10;

link X4 X2;

link X5 D2;

link X5 X6;

link X6 D2;

link X7 D2;

link X8 X5;

link X8 X7;

link X9 D1;

link X9 X1;

//Network Relationships: 

relation X9 X11 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3432876600908273 0.3319868349757884 0.45729055347657493 0.18297322034206248 0.10792371576807075 0.15378694530913467 0.31641879958028507 0.36262466205379074 0.08573727678172771 0.3286775605334559 0.5625540198675327 0.3824515016129864 0.34029354032888764 0.30538850297042086 0.4569721697416974 0.48834921912448154 0.3295222643643965 0.4637615530778789 );
}

relation X10 X12 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5532915342388656 0.6647133363319553 0.1886437896874175 0.7732790349646954 0.4467084657611344 0.3352866636680447 0.8113562103125824 0.2267209650353045 );
}

relation X11 X10 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.26938133457252933 0.47644880452461824 0.45009651878975576 0.09367041986751312 0.6262427852272205 0.12427757718258726 0.14027359902828349 0.8913203504656972 0.10437588020025021 0.3992736182927944 0.40962988218196067 0.015009229666789643 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5533595401608485 0.4466404598391515 );
}

relation X13 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4201222544463703 0.5333143562553262 0.5798777455536297 0.46668564374467375 );
}

relation X5 D1 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.067777894422365 0.4972450838895689 0.7093091431609505 0.33566435260419664 0.5322599859816368 0.1899561030738484 0.932222105577635 0.5027549161104311 0.29069085683904955 0.6643356473958034 0.4677400140183631 0.8100438969261515 );
}

relation X6 D1 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6650799218071485 0.6883677667663929 0.8368037908932906 0.4651982886687049 0.21189687132554944 0.5905926182531396 0.33492007819285147 0.3116322332336071 0.16319620910670937 0.5348017113312952 0.7881031286744505 0.4094073817468603 );
}

relation X7 X1 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5080931651799591 0.5072527059223263 0.8247802824789424 0.2939854819050723 0.20075149684407093 0.4291586382495121 0.4919068348200409 0.4927472940776737 0.17521971752105756 0.7060145180949278 0.7992485031559292 0.5708413617504879 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6665260784945193 0.3334739215054808 );
}

relation X1 D1 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3424002075215996 0.17981860263844596 0.1345859577590814 0.545807832544929 0.6497444806063247 0.3154375681159363 0.18851420479711378 0.3251637045657848 0.5639564231653693 0.5222138840239801 0.37845215930359155 0.19270607602612166 0.18304053420669164 0.07735340617892777 0.3430484697303687 0.16832890513164572 0.6198879011493784 0.3127067752031051 0.13538590845442036 0.4417292380579625 0.6727079662147969 0.27115163324837943 0.27290211321474744 0.34151396215369506 0.6431568900712406 0.05494839428483659 0.12333680163152562 );
}

relation X2 X12 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4717382646307914 0.3363621316627105 0.7170500470204086 0.42227483063106314 0.2731324198511238 0.5199559416319907 0.5282617353692086 0.6636378683372894 0.28294995297959136 0.5777251693689369 0.7268675801488762 0.48004405836800934 );
}

relation X3 D2 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.48434331288132293 0.5104466045388928 0.7117678471740677 0.5743698436875176 0.515656687118677 0.4895533954611073 0.2882321528259323 0.42563015631248236 );
}

relation X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4755855110617363 0.4192962813594618 0.10511820757880179 );
}

relation V0 D1 D2 X1 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (130.86902473067278 145.1722894093273 172.2129537350899 118.6409344785751 132.94419915722963 159.98486348299227 161.3465797935194 175.64984447217392 202.69050879793656 149.11848954142172 163.42175422007625 190.4624185458389 151.96965468226355 166.27291936091808 193.31358368668072 139.74156443016588 154.0448291088204 181.08549343458304 );
}

}
