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
num-states = 3;
states = (x10_1 x10_2 x10_3);
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
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (x12_1 x12_2 x12_3 x12_4);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (x13_1 x13_2 x13_3 x13_4);
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
num-states = 3;
states = (x16_1 x16_2 x16_3);
}

node D1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =2;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (d1_1 d1_2 d1_3 d1_4);
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
num-states = 3;
states = (x7_1 x7_2 x7_3);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x8_1 x8_2 x8_3);
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
num-states = 3;
states = (d2_1 d2_2 d2_3);
}

node X1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 5;
states = (x1_1 x1_2 x1_3 x1_4 x1_5);
}

node X2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x2_1 x2_2 x2_3);
}

node X3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x3_1 x3_2 x3_3);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (x4_1 x4_2 x4_3 x4_4);
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
values= table (0.1888496261497879 0.14520701658321447 0.9979449689937068 0.08437188411724805 0.0 0.0 0.31460887294538475 1.0 0.9122394427864341 0.41160439691067396 0.958893840814288 0.09597727281655416 0.7590938986210485 0.2418847043319648 0.0020550310062933033 0.4549102692213739 0.0 0.45040508950478314 0.3229889046002241 0.0 0.0 0.11893373280416522 0.0 0.6534150908178662 0.05205647522916363 0.6129082790848208 0.0 0.46071784666137805 0.0 0.5495949104952168 0.3624022224543912 0.0 0.08776055721356588 0.46946187028516084 0.04110615918571196 0.2506076363655797 );
}

relation X11 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.025985031926998452 0.5118062999758964 0.21218968838917643 0.9740149680730016 0.1520417705947895 0.4992992553144294 0.0 0.3361519294293141 0.2885110562963941 );
}

relation X12 X6 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.6991365016937837 0.0 0.0 0.44727250563923954 0.08424707768885449 0.7753797868411128 0.07244664259379949 0.9614051111582309 0.6051489039896345 0.13899719970554664 0.008860724294366015 0.0 0.0 0.03859488884176906 0.030397915923406287 0.4133288235057372 0.17563759333744086 0.2246202131588873 0.2284168557124167 0.0 0.3644531800869592 4.0147114947671625E-4 0.7312546046793386 );
}

relation X13 X6 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.037621356886047286 0.0 0.0 0.9352482219581062 0.3144927005784113 0.31939229698955646 0.0 0.0 0.5578623712130959 0.008469471915006507 0.023486612740412905 0.00884496239317302 0.19136454536955136 0.27508905414175633 0.9796097798881573 0.3974127931331475 0.15673339316052018 0.5474976264315038 0.03143319610596926 0.0 0.4941427540520373 0.056755998214864344 0.02039022011184268 0.18772171911117083 0.24778287874033667 0.44403290165348963 0.9450801911536179 0.055906815648720895 0.0 0.34876265065382284 0.0 0.4148654877556817 );
}

relation X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8359822471507045 0.16401775284929557 );
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
values= table (0.9859354476620366 0.014064552337963379 0.0 );
}

relation X5 X2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3181042979727704 0.7557344892046368 0.4696128195252715 0.0 0.2205935578010303 0.20773965385392174 0.7498292728611228 1.0 0.14545166322570863 0.5110427272981094 1.0 1.0 0.6818957020272296 0.2442655107953631 0.5303871804747284 0.0 0.7794064421989697 0.7922603461460782 0.2501707271388772 0.0 0.8545483367742914 0.48895727270189066 0.0 0.0 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.97536068410864 0.024639315891360012 );
}

relation X7 X5 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.24757870003790997 0.5492990176713364 0.6150205130242875 0.2032225543152356 0.15203809810286112 0.9840926803441208 0.0 0.017087666119179667 0.8986428289485174 0.0 0.09988133825960886 0.0 0.0 5.324429009169352E-4 0.8933579622475707 0.6736672943990277 0.0 0.0 0.34945000184415387 0.13391811723650612 0.6693265460551582 0.0 0.0 0.6461410011805918 0.0 0.0679904722422789 0.45239756079039106 0.0 0.5029749960208509 0.9994675570990831 0.10664203775242924 0.32633270560097244 0.7524212999620901 0.45070098232866374 0.03552948513155849 0.6628593284482583 0.17863535584198073 0.015907319655879305 1.0 0.3367713327002284 0.1013571710514826 0.9320095277577211 0.44772110095000023 1.0 0.49702500397914906 0.0 0.0 );
}

relation X8 X5 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.18588213564233202 0.007638936354670224 0.6884780191717996 0.6268543916667623 0.0 0.1926932871349332 0.44001517553285596 0.15526701731021522 0.47644914898156526 0.755846178384838 0.0 0.09266111310349867 0.2044541852636394 0.0 0.0 0.0 0.7214273324919793 0.0 0.3095508931975034 0.3729751350393012 1.0 0.626416369400709 0.5564502629460043 0.1518498331807469 0.4790582611150723 0.24415382161516205 0.0 0.26842620062889416 0.7955458147363605 0.5299813722315156 0.0 0.0 0.09269053186568872 0.9923610636453298 0.001971087630697037 1.7047329393653305E-4 0.0 0.18089034346435767 0.003534561521139661 0.6928831495090378 0.044492589903362495 0.0 1.0 0.6389126862676071 0.0 0.4700186277684843 1.0 1.0 );
}

relation X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5004112276371803 0.5533842628107005 0.4031901403563708 0.8951587118776211 0.4995887723628199 0.4466157371892993 0.5968098596436291 0.10484128812237882 );
}

relation X1 D2 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8298742275384459 0.0 0.6112504353695449 0.0035451145806456932 0.0 0.6147099028738804 0.0 0.0 0.0013770541658836117 1.6447477799394833E-4 0.8124752370892926 0.2631019712230279 0.6167493037522486 8.366659309323256E-4 9.005205020310343E-4 0.6108552855407883 0.3586478220205611 0.5577379369578431 0.029119019811110817 0.0 0.26239762809754197 0.0 0.0 0.05495930380728137 7.632219521169507E-4 0.6108024564350094 0.4897967383127196 0.031389019418255754 9.747635049853265E-4 0.0 0.46374977312561544 0.9683195286338769 0.055071993773509194 0.012725482985111725 6.157010890589535E-4 0.9567383517829623 0.0 0.0 0.10551555182267541 0.01982288253137052 0.0 0.10399049903943483 0.8381516025432358 0.08537173400244068 0.002297768991415582 0.23899782424677168 0.9450284453616385 0.7441708865410332 0.015194101178635065 0.19188190846614595 0.3601099132646493 0.3097624478020192 0.0 0.07528147232194948 0.27798405670665105 0.6943258175837772 0.10241310755639219 0.37953781630610867 0.00331293360774303 0.0026700780262979298 0.0 0.9751768534122023 0.012928980098839686 0.1035921905701063 0.0 0.0 0.0 0.17968267807714433 0.1720161288145446 0.0 0.905790325753277 0.0 0.0 0.11359763930249742 0.029204488322112497 0.5004591264462918 0.4366153944689657 0.0 0.5042289738966086 0.001081356587507605 0.8673070167353724 0.0 0.0 0.020490175173904866 0.011737104050166258 0.5536342869785352 0.0 0.043991976854691514 0.44931073503439234 0.017320307330857574 0.0 0.6114225956137392 0.0 0.12224021465988509 0.022819978551508094 0.0 0.060635107362335725 0.46267174144917345 0.012290584452541211 0.001986740079681001 0.9265076714942488 0.2798716888671337 0.39426167800273015 0.10429411590070936 0.004940292600907157 0.5985349313020962 0.18421182930296437 0.02579683385865737 0.021871454103447464 8.567213881379162E-4 0.40809345434095756 0.1575616029896352 0.42604461467457144 0.013852497010424885 0.212019625682546 0.0 0.5655862430879134 0.0 0.04206878672278807 0.007785187534390804 0.8495410896954252 0.1945604424170109 0.0 0.20487861616253203 0.0015084586161728947 0.42729292525983065 0.0 0.030599114778615422 0.002524061849411477 0.06513254075030604 0.8496390331590653 0.0 0.0 0.0288778169490748 0.0 0.848735129021643 0.0 0.0 0.0011740960418788536 0.06807012333440655 0.417603762736001 0.40919727654949134 0.0 0.23939592381062763 0.00363337436628937 0.25141554464949323 0.0 0.022055906196133016 0.0 0.01766288235455104 0.23350877993641886 0.0 0.3220274973102409 0.003796679388657369 0.0 0.7053861917922492 0.0 0.02312975926872749 0.0037306090265500505 0.0030116402964944396 0.02968047300338928 0.4284095660317322 0.0 0.0 0.0 0.0 0.04010178333608096 0.25254092506213166 0.14969568835245786 0.0 0.4809987733651679 0.2608385770363321 0.20010939585473536 0.0965917396380833 0.02701942479198471 0.0 0.0698535359471506 0.9221419762645823 0.14974526575187563 0.0 0.94191208869984 0.39035459964375685 0.0 0.03650089792750963 0.04020760074198821 0.8173974066919584 0.1445654151927426 0.11857087339411299 0.029190868994270897 0.002675580418819922 0.03215157608685325 0.0 0.09066318955429395 0.09403080543518744 0.016349066913264435 0.662649791341521 0.07349232850575119 0.012474053582485474 0.09424548535419987 0.2013800665155135 0.5692420483665761 0.01796609822514365 0.0 0.0030449250997678172 0.361379242144304 0.0 0.5743464360316217 0.12497928060297585 0.18562709030147811 0.0 0.7588613545063432 0.8203173219228557 0.0 0.0 0.012039104187853918 0.6847145835961962 0.0 0.08103946184548234 0.0 0.002434660936588326 0.3607919875551407 0.47611533510208603 0.0050018281857912485 0.0 0.005243391694556351 0.0 0.0 0.022771473043132887 0.046350807249993846 0.02713329642863315 0.8944844481773245 0.050949113664785335 0.5104816642236195 0.061291786937749296 0.016108886222142703 0.11656467365530074 0.5509075992783126 0.22688910412503208 0.0 0.016433189648339147 );
}

relation X2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7924390149969955 1.0 0.15764488186231193 0.6018934573381522 0.03592020704445436 0.0 0.8423551181376882 0.39791048418962943 0.17164077795855012 0.0 0.0 1.9605847221831904E-4 );
}

relation X3 X10 X13 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.39175031985799413 0.298845786532129 0.2387049117102027 0.5843597345846799 0.3142832233842708 0.5626801076815403 0.2835589774835275 0.35530449307737705 0.17755190635088072 0.5174747655319512 0.7374685104441986 1.0 0.02724126453327582 0.03774655653006922 0.2748333541950972 0.1477934111367384 0.3580492702532148 0.5118959645198027 0.024953768560508453 0.02394416813890481 0.0 0.5053987414428252 0.5898420564891489 0.0 0.17584566227586076 0.03377075274764734 1.0 0.47616173067263323 0.4578892675821399 0.34747627100035255 0.0 0.0 0.27518549911304574 0.8489681379978435 0.5455780969038119 0.5584967404072347 0.0 0.0 0.41270860988144503 0.02272906014356985 0.8805244169899559 0.8242495153710371 0.12509720577231037 0.008666902914577002 0.41186289868567844 0.647889776482358 0.06903261669364319 0.1536811187820161 0.36311293325327115 0.6680206763997182 0.7612950882897973 0.41564026541532006 0.04668812663329291 0.4373198923184597 0.2283951061413706 0.33146727941132254 0.5924750040356436 0.27754899213313144 0.2625314895558014 0.0 0.0 0.0 0.6689680962874881 0.31203640373836045 0.6419507297467852 0.4881040354801974 0.6086320889505065 0.01470022252231989 0.0 0.0 0.0 0.09709490062511372 0.5151903125007685 0.69706169502252 0.0 0.09197072132995739 0.10019681799490414 0.43155711938348573 1.0 0.8742609449380868 0.4411161429963346 0.15103186200215651 0.05136598210467153 0.4415032595927653 0.22823396825994313 0.0 0.41461409163521384 0.0 0.11947558301004411 0.0 0.0 0.10163526717599279 0.46455937943015185 0.352110223517642 0.44390809112927004 0.8463188812179839 0.2451367468887347 0.03313353706815286 0.0 0.0 0.6390286499824364 0.0 0.4880459163751018 0.3132282275113005 0.22997308961347557 0.20497624233491743 0.0 0.0 0.9727587354667242 0.9622534434699308 0.05619854951741468 0.5401701851249011 0.0 0.0 0.36641414248898513 0.9613556093387753 1.0 0.49460125855717485 0.41015794351085116 0.9029050993748863 0.30896402522337085 0.2691675522298327 0.0 0.43186754799740945 0.441913914422956 0.22096660961616163 0.0 0.12573905506191316 0.2836983578906197 0.0 0.40305592099151666 0.0 0.7717660317400569 1.0 0.172677298483341 0.9772709398564301 0.0 0.17575048462896278 0.8749027942276896 0.8896978299094302 0.12357772188416985 0.0 0.48705929217708666 );
}

relation X4 X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6205369335132078 0.27365193637577373 0.0 0.00890545112519412 0.09274187900840354 0.41222009274717647 0.5417595667598034 0.8063745436120042 0.0 0.32667973948016277 0.11272341719435462 0.018192605440941787 0.3794630664867921 0.28518492997332123 0.20252743943458107 0.14742985962543226 0.3760698002764553 0.5309534749911354 0.05799757498445781 0.17245268262799326 0.3744854879187866 0.0011467729302291076 0.5189224412042838 0.6285638547860873 0.0 0.3826583038595828 0.494612646704721 0.8363924072190301 0.510966012579055 0.0 0.0 0.02117277376000258 0.4473763919739168 0.0 0.3683541416013617 0.29643050647753877 0.0 0.05850482979132226 0.3028599138606979 0.007272282030343542 0.020222308136086144 0.056826432261688035 0.4002428582557388 0.0 0.17813812010729668 0.6721734875896083 0.0 0.05681303329543199 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (0.0 110.50604048200734 0.0 65.77483082057623 58.72884629195863 0.0 92.93036866612981 85.88438413751221 0.0 113.18378823866242 0.0 104.25246804239909 );
}

}