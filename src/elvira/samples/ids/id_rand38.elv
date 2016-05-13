// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

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
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x11_1 x11_2);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x12_1 x12_2 x12_3);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x13_1 x13_2 x13_3);
}

node X14(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x14_1 x14_2);
}

node X15(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x15_1 x15_2);
}

node X16(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x16_1 x16_2);
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
pos_x =4;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x5_1 x5_2);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
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
pos_x =4;
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

link D1 X2;

link D1 X3;

link D1 X4;

link D1 X5;

link D1 X7;

link D1 X8;

link D1 X9;

link D2 V0;

link D2 X1;

link X10 D1;

link X10 X11;

link X10 X3;

link X11 D1;

link X11 X12;

link X12 D1;

link X12 X13;

link X13 D1;

link X13 X3;

link X14 D1;

link X14 X10;

link X15 D1;

link X15 X10;

link X16 X10;

link X2 X5;

link X3 X4;

link X4 X1;

link X5 D2;

link X5 X7;

link X5 X8;

link X6 D2;

link X6 X12;

link X6 X13;

link X7 D2;

link X8 D2;

link X9 D2;

link X9 X7;

link X9 X8;

//Network Relationships: 

relation X10 X15 X16 X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.15981818869162664 0.9532005050763017 0.5382542008684903 1.0 0.0 0.0 0.14763184504697074 0.0 0.8401818113083734 0.04679949492369823 0.4617457991315097 0.0 1.0 0.0 0.8523681549530292 );
}

relation X11 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8419869430216037 0.05810037416335973 0.15801305697839618 0.9418996258366403 );
}

relation X12 X6 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9757865386832104 0.0 0.17711601470431232 0.0 0.0 0.16809320451776974 0.7498525190108639 3.5491142447752465E-4 0.0242134613167895 0.8319067954822303 0.07303146628482379 0.9996450885755225 );
}

relation X13 X6 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 9.422504657671799E-4 0.5549404836450896 0.30433022459389386 0.46449588838801176 0.0 1.0 0.9792265523886756 0.14635021538981094 0.0 0.5355041116119883 0.0 0.0 0.019831197145557342 0.2987093009650994 0.6956697754061061 0.0 );
}

relation X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 );
}

relation X15 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 );
}

relation X16 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3148592391811865 0.6851407608188135 );
}

relation X5 X2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.3859728696226013 0.3625116511041463 0.9724081858775969 0.2931165677876982 0.9229566856866258 1.0 0.6140271303773986 0.6374883488958538 0.027591814122403104 0.7068834322123019 0.07704331431337419 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7000892805886503 0.2999107194113496 );
}

relation X7 X5 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 1.0 0.5057888311080545 0.0 0.4142702395888952 0.5074354822412507 1.0 0.24822646007824023 0.7148532766651334 0.0 0.6518217872005986 1.0 0.0 0.0 0.4942111688919455 1.0 0.5857297604111048 0.49256451775874943 0.0 0.7517735399217598 0.2851467233348666 0.0 0.3481782127994014 0.0 );
}

relation X8 X5 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.6831280036491776 0.26399612318138893 0.5251956484436562 0.6567306248719739 0.1502375522191567 0.7176984288762304 0.3048814529862316 0.0 0.8614895809375565 1.0 1.0 1.0 0.31687199635082236 0.7360038768186111 0.4748043515563439 0.34326937512802624 0.8497624477808432 0.2823015711237696 0.6951185470137684 1.0 0.13851041906244355 0.0 0.0 );
}

relation X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 0.9000197082160648 0.0 1.0 0.09998029178393517 );
}

relation X1 D2 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9313300347045663 0.0 0.16202479084051746 0.0 0.0 0.14632674268757428 0.5301865615878035 0.019429721907053225 0.04414932151029621 0.5345680829103964 0.22769268432837256 0.06985764983452063 0.3040088929632012 0.0 0.0 0.19450112421667054 0.4147622294801304 0.053930427014705414 0.06866996529543365 0.33632455196903194 0.7684897796278833 0.0 0.0 0.8536732573124257 0.28264623067735273 0.9569451878864744 0.9558506784897037 0.11232950366296526 0.6203175240424718 0.8211188918755864 0.32167450528976393 0.0 1.0 0.6084850094715861 0.29330070549950404 0.005191046338126909 0.0 0.663675448030968 0.0694854295315993 1.0 1.0 0.0 0.18716720773484377 0.023625090206472275 0.0 0.3531024134266385 0.15198979162915574 0.10902345828989302 0.37431660174703485 1.0 0.0 0.19701386631174347 0.2919370650203656 0.9408785266471676 );
}

relation X2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6934594828669479 0.7797162646603375 0.0 0.306540517133052 0.22028373533966247 0.0 );
}

relation X3 X10 X13 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.70154735187401 0.0 1.0 0.0 0.0 1.0 1.0 0.6809867889955074 1.0 0.012938780079073332 0.0 1.0 0.0 1.0 0.7213605962203165 0.0 0.0 0.0 0.2984526481259901 0.0 0.0 0.0 0.0 0.0 0.0 0.3190132110044927 0.0 0.9870612199209267 1.0 0.0 1.0 0.0 0.2786394037796836 1.0 1.0 1.0 );
}

relation X4 X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.29575142255401016 0.575198824074504 0.4899500662000586 0.1157596824064616 0.8052062141978963 0.7970363032466333 0.394733371975403 0.04432549087357988 0.0 0.408568857866509 0.19479378580210363 0.055215491799318145 0.309515205470587 0.3804756850519162 0.5100499337999413 0.47567145972702934 0.0 0.14774820495404856 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (29.035111612341858 53.22620742927703 78.98881452142864 103.17991033836381 0.0 106.64762082994882 );
}

}
