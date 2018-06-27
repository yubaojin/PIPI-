/*
Navicat MySQL Data Transfer

Source Server         : banjiba
Source Server Version : 50718
Source Host           : rm-wz92lh3g7a9vu84otoo.mysql.rds.aliyuncs.com:3306
Source Database       : pipi

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-06-03 17:10:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动增长id',
  `openid` varchar(35) DEFAULT 'o3QX10JEZASEVKP0y94QnH8dKpv4' COMMENT '用户唯一id',
  `name` varchar(50) NOT NULL COMMENT '收件人名字',
  `mobile` varchar(20) NOT NULL COMMENT '电话',
  `gender` int(11) DEFAULT '1' COMMENT '1 - 男士 2 - 女士',
  `address` varchar(500) NOT NULL COMMENT '地址详情',
  `is_default` int(11) DEFAULT '0' COMMENT '0 - 不是默认地址 1 - 默认地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='地址表';

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('4', 'o3QX10JEZASEVKP0y94QnH8dKpv4', '余飞', '18828871539', '2', '四川省自贡市自流井区一对山商业城(汇东路) 四栋1010', '0');
INSERT INTO `address` VALUES ('5', 'o3QX10JEZASEVKP0y94QnH8dKpv4', '于百金', '18828871539', '1', '四川省自贡市自流井区汇兴路四川理工学院 四川理工学院4栋', '0');
INSERT INTO `address` VALUES ('6', 'o3QX10JEZASEVKP0y94QnH8dKpv4', '于百金', '18828871539', '1', '四川省自贡市自流井区汇兴路四川理工学院 四川理工学院4栋', '1');

-- ----------------------------
-- Table structure for disk
-- ----------------------------
DROP TABLE IF EXISTS `disk`;
CREATE TABLE `disk` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜id',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  `img` varchar(500) DEFAULT NULL COMMENT '图片地址',
  `title` varchar(100) NOT NULL,
  `price` float NOT NULL COMMENT '原价',
  `dis_price` float DEFAULT '0' COMMENT '优惠价',
  `sell_point` varchar(500) DEFAULT NULL,
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  `sellnum` int(11) DEFAULT '0',
  `status` int(4) NOT NULL DEFAULT '1' COMMENT '商品状态，1-正常，2-优惠中 3-下架，4-删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单商品';

-- ----------------------------
-- Records of disk
-- ----------------------------
INSERT INTO `disk` VALUES ('1', '2', 'https://fuss10.elemecdn.com/9/50/588069235cca8206f9212ca963776jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '青椒肉丝盖饭', '9', '9', '青椒肉丝盖饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('2', '2', 'https://fuss10.elemecdn.com/0/91/62d9d2e47aa00b695f4aaad465286jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '鱼香肉丝盖饭', '9', '9', '鱼香肉丝盖饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('3', '2', 'https://fuss10.elemecdn.com/6/bb/86fede6659ab466737cc7139009c7jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '莲白回锅肉盖饭', '9', '9', '莲白回锅肉盖饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('4', '2', 'https://fuss10.elemecdn.com/9/06/38f39e4129b9e739bf3ffa7a1daf9jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '土豆肉丝盖饭', '9', '9', '土豆肉丝盖饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('5', '2', 'https://fuss10.elemecdn.com/e/a1/04ac97e8a0e2f2e908a6494712999jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '碎肉茄子盖饭', '9', '9', '碎肉茄子盖饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('6', '2', 'https://fuss10.elemecdn.com/f/2a/ddf70efa2647caeef1ea5446afac0jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '土豆回锅肉盖饭', '9', '9', '土豆回锅肉盖饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('7', '2', 'https://fuss10.elemecdn.com/a/59/29539082e1973aaceadb710b98f97jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '豆干肉丝盖饭', '9', '9', '豆干肉丝盖饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('8', '2', 'https://fuss10.elemecdn.com/c/a1/62216b8be01e1f6579ec7069066fdjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '酸菜土豆肉丝盖饭', '9', '9', '酸菜土豆肉丝盖饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('9', '2', 'https://fuss10.elemecdn.com/7/a8/57590d4e3c34f913a11728ea097d4jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '蘑菇肉丝盖饭', '9', '9', '蘑菇肉丝盖饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('10', '2', 'https://fuss10.elemecdn.com/9/cf/f932de1fe0c24cb1798cce9d3a379jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '尖椒肉丝盖饭', '9', '9', '尖椒肉丝盖饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('11', '2', 'https://fuss10.elemecdn.com/b/6c/a4f3062c40a0cf81d214830f3f613jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '酸菜肉丝盖饭', '9', '9', '酸菜肉丝盖饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('12', '3', 'https://fuss10.elemecdn.com/a/ae/18dbb7fbd2fad45253f16a81708c6jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '麻辣豆腐', '9', '9', '不含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '1', '1');
INSERT INTO `disk` VALUES ('13', '3', 'https://fuss10.elemecdn.com/f/25/3d7ac8880476ae918fed66f2578bbjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '麻辣血旺', '9', '9', '不含饭', '2018-05-21 17:29:56', '2018-06-02 17:04:05', '2', '1');
INSERT INTO `disk` VALUES ('14', '3', 'https://fuss10.elemecdn.com/4/5d/e86e1fbffb2e892a22e96dba569eejpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '红烧茄子', '9', '9', '不含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('15', '3', 'https://fuss10.elemecdn.com/5/71/b20a799a0cb0194e3ee7a2a2d27e2jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '番茄炒蛋', '10', '10', '不含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('16', '3', 'https://fuss10.elemecdn.com/4/0f/c4102ee5d9d99e2f7d09e5335b41ajpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '青椒土豆丝', '8', '8', '不含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('17', '3', 'https://fuss10.elemecdn.com/d/75/18e96d9a0b7d61f2d2849b34e2f84jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '蒜苗回锅肉', '16', '16', '不含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('18', '3', 'https://fuss10.elemecdn.com/c/dd/bca978fe9211e04a57844d740d444jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '鱼香肉丝', '13', '13', '不含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('19', '3', 'https://fuss10.elemecdn.com/5/55/638c0a5e80466e0a3a43a331de1f1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '木耳肉片', '13', '13', '不含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('20', '3', 'https://fuss10.elemecdn.com/3/ed/3e56dd7533e56ba3c2eef5458c04cjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '青椒肉丝', '13', '13', '不含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('21', '3', 'https://fuss10.elemecdn.com/3/4b/d1e60f61e58e1811fc07e5c4d8028jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '玉米碎肉', '11', '11', '不含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('22', '3', 'https://fuss10.elemecdn.com/4/75/5266f0aafc8022310cc58c7e47f94jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '豆干肉丝', '12', '12', '不含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('23', '4', 'https://fuss10.elemecdn.com/1/9e/6a84608f4a9823cda28d41cf06821jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '小干锅五花', '20', '20', '含米饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('24', '4', 'https://fuss10.elemecdn.com/e/6b/a1107744fbb1db92bf8205f5de584jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '小干锅鸡翅', '25', '25', '含米饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('25', '4', 'https://fuss10.elemecdn.com/8/c8/9a8d599de8d66b6da793582844cd5jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '小干锅里脊', '24', '24', '含米饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('26', '4', 'https://fuss10.elemecdn.com/f/2b/d5f2a7fe2750df0feba78d507cbf5jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '小干锅兔', '25', '25', '含米饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('27', '4', 'https://fuss10.elemecdn.com/9/f6/6e05ae83a45288ce09ef6b16b9dccjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '小干锅排骨', '20', '20', '含米饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('28', '4', 'https://fuss10.elemecdn.com/8/47/f773191143188c91dc83452c55dd2jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '小干锅鸡', '18', '18', '含米饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('29', '4', 'https://fuss10.elemecdn.com/c/12/b3f041a429e432e9c862d70d28893jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '小干锅鸡柳', '20', '20', '含米饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('30', '4', 'https://fuss10.elemecdn.com/6/bd/3bdd5840e15b5aac076b3b728a41fjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '小干锅虾饺', '18', '18', '含米饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('31', '4', 'https://fuss10.elemecdn.com/1/7f/03da8beb8d903dc7e4295e994384bjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '小干锅虾', '17', '17', '含米饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('32', '4', 'https://fuss10.elemecdn.com/9/d1/0dbde174cbf44a80b93d516e388b7jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '小干锅牛肉', '28', '28', '含米饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('33', '4', 'https://fuss10.elemecdn.com/0/88/7f06c0bf50c360322847e3197159fjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '小干锅肥肠', '25', '25', '含米饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('34', '5', 'https://fuss10.elemecdn.com/9/11/1a0673f46d27ff3c869af4197e320jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '砂锅鱼豆腐', '8', '8', '含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('35', '5', 'https://fuss10.elemecdn.com/5/6f/f385607c0443235a209eef76c4616jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '心肺砂锅', '11', '11', '含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('36', '5', 'https://fuss10.elemecdn.com/9/b3/5538abb9b54e9b178af69eb6dcd6bjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '酥肉砂锅', '13', '13', '含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('37', '5', 'https://fuss10.elemecdn.com/2/25/0eab8382294fb891792088af83e75jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '肉片砂锅', '12', '12', '含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('38', '5', 'https://fuss10.elemecdn.com/7/be/0429ffad0dcabc4f6aa9ca7a20631jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '什锦砂锅', '12', '12', '里面有虾饺·脆皮肠·火腿肠', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('39', '5', 'https://fuss10.elemecdn.com/a/bd/26ae961f11c02e930f6e6ccc4fd5cjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '虾饺砂锅', '12', '12', '含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('40', '5', 'https://fuss10.elemecdn.com/e/a5/2baa836c8cadbf4608adb8b2b0b51jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '爽口玉米脆饺', '11', '11', '含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('41', '5', 'https://fuss10.elemecdn.com/6/bc/f935c5a65fcdc9002382b33edff9ejpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '撒尿牛丸砂锅', '12', '12', '含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('42', '5', 'https://fuss10.elemecdn.com/5/c2/4077530083f93d0f4375dfad8025fjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '鱼丸砂锅', '10', '10', '含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('43', '5', 'https://fuss10.elemecdn.com/a/6d/acec4ffae1accc6f5ddaff86fe1a6jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '香菜圆子砂锅', '10', '10', '含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('44', '5', 'https://fuss10.elemecdn.com/7/be/0429ffad0dcabc4f6aa9ca7a20631jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '脆皮肠砂锅', '11', '11', '含饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('45', '6', 'https://fuss10.elemecdn.com/b/38/30f1086fc6b2e4a68982bd71b699bjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '酥肉烫饭', '8.5', '8.5', '酥肉烫饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('46', '6', 'https://fuss10.elemecdn.com/4/77/939cfcc4ace7e38183b16f257bff4jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '番茄煎蛋烫饭', '8', '8', '番茄煎蛋烫饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('47', '6', 'https://fuss10.elemecdn.com/4/2e/24d8d7ec082c6bf93620634436eacjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '三鲜烫饭', '8', '8', '三鲜烫饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('48', '6', 'https://fuss10.elemecdn.com/a/7e/e48de51f4a7c92a6e0dd7622def5ajpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '火腿肠煎蛋烫饭', '8', '8', '火腿肠煎蛋烫饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('49', '6', 'https://fuss10.elemecdn.com/b/d7/8b6c2ad1f134ce961edc4a1f96d38jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '酸菜肉丝烫饭', '8', '8', '酸菜肉丝烫饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('50', '6', 'https://fuss10.elemecdn.com/4/14/73d112387c8b3fc4a260aeac55684jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '酸菜煎蛋烫饭', '8', '8', '酸菜煎蛋烫饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('51', '7', 'https://fuss10.elemecdn.com/1/59/4e132356aac4d35cfc36dfcf4e8dfjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '招牌辣白菜海鲜炒饭', '18', '18', '➕开胃菜➕汤', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('52', '7', 'https://fuss10.elemecdn.com/f/e1/9e18d97fd15d22a53b8456f8cfb40jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '欧巴海鲜炒饭', '18', '18', '➕开胃菜➕汤', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('53', '7', 'https://fuss10.elemecdn.com/6/92/2424672b33ff079f89f3da04d6adcjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '酱爆青虾炒饭', '18', '18', '➕开胃菜➕汤', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('54', '7', 'https://fuss10.elemecdn.com/a/5f/e117b88ee734dc5bbb15f47caed76jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '孜然鱿鱼炒饭', '16', '16', '➕开胃菜➕汤', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('55', '7', 'https://fuss10.elemecdn.com/2/3d/26826a2e4be92e3e3b96672947bbfjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '香肠腊肉炒饭', '16', '16', '➕开胃菜➕汤', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('56', '7', 'https://fuss10.elemecdn.com/8/9b/2c15371fa04dd372b1f89c7739f84jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '野山椒牛肉丝炒饭', '16.8', '16.8', '➕开胃菜➕汤', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('57', '7', 'https://fuss10.elemecdn.com/c/7e/25fd3fbe0f1c6740e56305dd75589jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '懒人酱油炒饭', '14', '14', '➕开胃菜➕汤', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('58', '7', 'https://fuss10.elemecdn.com/f/6f/a72e59d98336f3169a69de930cc0ejpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '豆豉小鱼炒饭', '14', '14', '➕开胃菜➕汤', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('59', '7', 'https://fuss10.elemecdn.com/1/a9/4e31c625c5002248e88dcd18505bcjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '甜椒肉丝炒饭', '14', '14', '➕开胃菜➕汤', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('60', '7', 'https://fuss10.elemecdn.com/f/d4/3a1a3eb4afef050df499439dc2cdfjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '芽菜碎肉炒饭', '14', '14', '➕开胃菜➕汤', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('61', '7', 'https://fuss10.elemecdn.com/1/47/f26811e30e8b5284c3baf4cfca7a7jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '五色扬州炒饭', '12.8', '12.8', '➕开胃菜➕汤', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('62', '8', 'https://fuss10.elemecdn.com/8/71/31494988fbca8cc8ed9ce5045f56bjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '红烧牛肉粉', '8', '8', '中份', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('63', '8', 'https://fuss10.elemecdn.com/2/aa/a4308ca649aa7321f275affab8dd8jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '红烧牛肉米线', '8', '8', '中份', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('64', '8', 'https://fuss10.elemecdn.com/6/41/7162c6fb32720d300ca6648e12b6djpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '红烧牛肉面', '8', '8', '中份', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('65', '8', 'https://fuss10.elemecdn.com/0/5f/9a1e9713ab9b7055ff87605722aecjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '酸菜肉丝面', '8', '8', '中份', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('66', '8', 'https://fuss10.elemecdn.com/6/c9/6c7a16e052cf5b7ddcd0f524ad92bjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '西红柿鸡蛋面', '8', '8', '中份', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('67', '8', 'https://fuss10.elemecdn.com/2/e3/46d4ca42419f3cdb43a40e94514d5jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '炸酱干拌面', '8', '8', '中份', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('68', '8', 'https://fuss10.elemecdn.com/1/4a/828956f61b6e75ea96d8daf6f8dcdjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '鸡杂干拌面', '8', '8', '中份', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('69', '8', 'https://fuss10.elemecdn.com/c/3d/f5534d22e592bfab538dd03a412ebjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '素干拌然面', '8', '8', '中份', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('70', '8', 'https://fuss10.elemecdn.com/f/28/ed988b0f64f96f31697ae8b4a1aafjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '生椒牛肉干拌面', '8', '8', '中份', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('71', '9', 'https://fuss10.elemecdn.com/5/8c/30feb444c56ba802ed8773321d700jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '骨髓鲜肉包', '6', '6', '8个', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('72', '9', 'https://fuss10.elemecdn.com/9/06/036c96b3ab97e1229d00ddbe368c0jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '麻辣牛肉包', '6', '6', '8个', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('73', '9', 'https://fuss10.elemecdn.com/f/0f/f44f9836f2ab19c6861da19128ae4jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '红烧酱肉包', '6', '6', '6个', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('74', '9', 'https://fuss10.elemecdn.com/f/0a/da7216057c6af781c921b47cf3a17jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '珍珠烧麦', '6', '6', ' 8个', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('75', '9', 'https://fuss10.elemecdn.com/2/e1/5fd40681233458d2478038f67aa36jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '特色蒸饺', '6', '6', '8个', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('76', '9', 'https://fuss10.elemecdn.com/8/d9/7a88bd170f23f169e209760d954cfjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '韭菜蒸饺', '6', '6', '8个', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('77', '9', 'https://fuss10.elemecdn.com/7/52/70841ae23de4787e88cc7b38ee203jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '生煎锅贴饺', '6', '6', '7个', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('78', '9', 'https://fuss10.elemecdn.com/d/60/87ca59c7c40af0d4806da42052d65jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '青菜稀饭', '2', '2', '青菜稀饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('79', '9', 'https://fuss10.elemecdn.com/8/56/0dbb4002cddac05d114bcc339996fjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '红苕稀饭', '2', '2', '红苕稀饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('80', '9', 'https://fuss10.elemecdn.com/c/ae/4e2a7e34768e55d1f802e8066966ejpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '新鲜豆浆', '2', '2', '新鲜豆浆', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('81', '9', 'https://fuss10.elemecdn.com/e/ef/c503d6d6a469c97ba01481bdf5bd4jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '特色海带汤', '2', '2', '特色海带汤', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('82', '10', 'https://fuss10.elemecdn.com/a/49/07d3cec50401f321cf175b2c6a604jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '优格椰汁奶茶', '10', '10', '中杯', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('83', '10', 'https://fuss10.elemecdn.com/c/ff/e7687bd8b3186db8bad9ce414e2c5jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '手工芋圆奶茶', '10', '10', '手工芋圆奶茶', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('84', '10', 'https://fuss10.elemecdn.com/b/cf/7b37684d6829a102e6df00f69ee27jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '西米露奶茶', '10', '10', '西米露奶茶', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('85', '10', 'https://fuss10.elemecdn.com/9/de/225a773c12eda3e282c865a6b8a99jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '红豆布丁奶茶', '9', '9', '红豆布丁奶茶', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('86', '10', 'https://fuss10.elemecdn.com/0/9f/c4e04df858b656d1238201e1c6a81jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '大满贯布丁奶茶', '9', '9', '大满贯布丁奶茶', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('87', '10', 'https://fuss10.elemecdn.com/c/e5/744c03eb679c7e7575ac10dc3e23bjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '奥利奥奶茶', '9', '9', '奥利奥奶茶', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('88', '10', 'https://fuss10.elemecdn.com/1/1e/280366766fdfca3515ce59f77ded4jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '椰香奶茶', '9', '9', '椰香奶茶', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('89', '10', 'https://fuss10.elemecdn.com/5/b6/9973acea1aeb65efb0622d58f3035jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '红豆奶茶', '9', '9', '红豆奶茶', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('90', '10', 'https://fuss10.elemecdn.com/9/d5/4f38e240ff0b4f7ab8b58df17d5f1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '咖啡奶茶', '9', '9', '咖啡奶茶', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('91', '10', 'https://fuss10.elemecdn.com/2/04/5b25a1ca530f5e63613244db95f0cjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '抹香奶茶', '9', '9', '抹香奶茶', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('92', '10', 'https://fuss10.elemecdn.com/9/4c/a0b8068efaa27efe2a708b6db66e6jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '西瓜奶茶', '9', '9', '西瓜奶茶', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('93', '10', 'https://fuss10.elemecdn.com/b/1f/b6e7f2116c34ab734d2073d803627jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '阿化田奶茶', '8', '8', '阿化田奶茶', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('94', '10', 'https://fuss10.elemecdn.com/3/37/27a397eb5fbb81e6bf8eba6444e05jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '英式皇家奶茶', '11', '11', '英式皇家奶茶', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('95', '10', 'https://fuss10.elemecdn.com/a/16/22c3d72d7efc6025e2d45a4ee5ef4jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '烧仙草奶茶', '7', '7', '烧仙草奶茶', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('96', '11', 'https://fuss10.elemecdn.com/2/5e/d47d61a3815cd1c373df0b9dae2a3jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85', '米饭', '1', '1', '米饭', '2018-05-21 17:29:56', '2018-05-21 17:29:56', '0', '1');
INSERT INTO `disk` VALUES ('97', '20', 'https://canteentakeout.oss-cn-shenzhen.aliyuncs.com/pics/c9a51ec60bcd4653ac8776630f361cf8.jpg', '羊肉串', '5', '5', '超辣超带感', '2018-06-02 14:30:13', '2018-06-02 14:32:29', '1', '1');

-- ----------------------------
-- Table structure for evaluate
-- ----------------------------
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评价id',
  `disk_id` int(11) NOT NULL COMMENT '评级的菜的id',
  `order_id` varchar(50) NOT NULL DEFAULT '123456756756' COMMENT '订单的id',
  `openid` varchar(35) DEFAULT 'o3QX10JEZASEVKP0y94QnH8dKpv4' COMMENT '微信用户的开放id唯一',
  `nickname` varchar(100) DEFAULT NULL COMMENT '评价人昵称',
  `avatar_url` varchar(500) DEFAULT NULL COMMENT '评价人头像',
  `content` varchar(500) NOT NULL COMMENT '评价的描述',
  `imgs` varchar(500) DEFAULT NULL COMMENT '评价时加的照片',
  `is_anoymous` int(11) DEFAULT '1' COMMENT '是否匿名 1 - 匿名 0- 不匿名',
  `eval_Value` int(11) DEFAULT '3' COMMENT '评价值 1 - 差评 2 - 中评 3 - 好评',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='评价表';

-- ----------------------------
-- Records of evaluate
-- ----------------------------
INSERT INTO `evaluate` VALUES ('1', '1', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '按时间肯定会 。', 'https://canteentakeout.oss-cn-shenzhen.aliyuncs.com/pics/4c690584d94e40609a2ac3d4c3405f04.jpg,https://canteentakeout.oss-cn-shenzhen.aliyuncs.com/pics/4c690584d94e40609a2ac3d4c3405f04.jpg,https://canteentakeout.oss-cn-shenzhen.aliyuncs.com/pics/4c690584d94e40609a2ac3d4c3405f04.jpg', '1', '1', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('2', '1', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '按时间肯定会阿萨德好几款卡萨丁 。', 'https://canteentakeout.oss-cn-shenzhen.aliyuncs.com/pics/4c690584d94e40609a2ac3d4c3405f04.jpg,https://canteentakeout.oss-cn-shenzhen.aliyuncs.com/pics/4c690584d94e40609a2ac3d4c3405f04.jpg', '1', '2', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('3', '2', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '飒飒大家 按时打卡机 奥斯卡单机 ', null, '1', '3', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('4', '3', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '阿斯蒂芬答案阿阿斯蒂芬', null, '1', '3', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('5', '4', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '阿斯蒂芬阿斯蒂芬', null, '1', '1', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('6', '5', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '安抚', null, '1', '3', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('7', '6', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '阿斯蒂芬', null, '1', '2', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('8', '7', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '撒地方阿斯蒂芬 阿斯蒂芬暗室逢灯放得吗，哈煽风点火驾驶的贷款合计 ', null, '1', '3', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('9', '8', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '是打发计划卡淡饭黄齑驾驶的飞机客户阿双方合计', null, '1', '1', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('10', '9', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '阿斯蒂芬两节课啊撒地方环境家里回复', null, '1', '1', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('11', '10', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '阿斯蒂芬打手机客户爱神的箭kh', null, '1', '3', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('12', '10', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '阿斯蒂芬静安寺打回访客户', null, '1', '3', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('13', '11', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '阿斯蒂芬很坑啊撒地方环境阿斯蒂芬客户撒反对', null, '1', '2', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('14', '11', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '打扫房间看哈撒地方环境贾师傅带客户经理', null, '1', '3', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('15', '12', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '阿斯蒂芬很坑阿斯蒂芬建行卡阿斯蒂芬客户就', null, '1', '2', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('16', '12', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '士大夫见客户阿斯蒂芬客户就卡收费的环境发神经', null, '1', '3', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('17', '1', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '阿斯蒂芬结婚后阿斯蒂芬客户就阿斯顿分开', 'https://canteentakeout.oss-cn-shenzhen.aliyuncs.com/pics/4c690584d94e40609a2ac3d4c3405f04.jpg', '1', '3', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('18', '2', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '阿斯蒂芬客户就奥斯卡积分落户阿双方尽快回来阿双方尽快回来', null, '1', '2', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('19', '3', '123456756756', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, '阿斯蒂芬客户就阿斯蒂芬客户就暗室逢灯客户经理阿萨肺力咳合剂份', null, '1', '3', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('20', '12', '1526894933660018040', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, 'afasds', 'https://canteentakeout.oss-cn-shenzhen.aliyuncs.com/pics/4c690584d94e40609a2ac3d4c3405f04.jpg,https://canteentakeout.oss-cn-shenzhen.aliyuncs.com/pics/849c7bce60a347eb9384ee5495960cd5.jpg', '1', '3', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('21', '13', '1526894933660018040', 'o3QX10JEZASEVKP0y94QnH8dKpv4', null, null, 'asdasasd', 'https://canteentakeout.oss-cn-shenzhen.aliyuncs.com/pics/6c5ac3a2c3c545f38ea2b3f3a8864dd4.jpg,https://canteentakeout.oss-cn-shenzhen.aliyuncs.com/pics/df020c9e38e7476aa1f490f295824b7d.jpg', '1', '3', '2018-06-02 14:04:17', '2018-06-02 14:04:17');
INSERT INTO `evaluate` VALUES ('22', '97', '1527921083507545560', 'o3QX10JEZASEVKP0y94QnH8dKpv4', 'Yuo_o', 'https://wx.qlogo.cn/mmopen/vi_32/DOYEvJQR9rEiaakuMCeZsL6G1WKrnjiaCE0qv8fn5tVjMhCvwiagGN92ovWZfTlTTYck1AHvyBIKf1b20X2zich5sA/132', '好吃啊  啊 啊啊 啊啊', 'https://canteentakeout.oss-cn-shenzhen.aliyuncs.com/pics/4fd6a65d68924ec6a2ff30b350574c31.jpg', '0', '2', '2018-06-02 14:32:55', '2018-06-02 14:32:55');
INSERT INTO `evaluate` VALUES ('23', '13', '1527930132488008594', 'o3QX10JEZASEVKP0y94QnH8dKpv4', 'Yuo_o', 'https://wx.qlogo.cn/mmopen/vi_32/DOYEvJQR9rEiaakuMCeZsL6G1WKrnjiaCE0qv8fn5tVjMhCvwiagGN92ovWZfTlTTYck1AHvyBIKf1b20X2zich5sA/132', '哈哈好啊', 'https://canteentakeout.oss-cn-shenzhen.aliyuncs.com/pics/7478482977794e208e01f4218b65b3bf.jpg,https://canteentakeout.oss-cn-shenzhen.aliyuncs.com/pics/902a4dc5d8d446fb96a44708cd6ec97f.jpg', '0', '3', '2018-06-02 17:04:19', '2018-06-02 17:04:19');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `parent_id` int(11) NOT NULL DEFAULT '1',
  `is_parentId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜品目录';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '总菜单', '0', '1');
INSERT INTO `menu` VALUES ('2', '盖饭', '1', '0');
INSERT INTO `menu` VALUES ('3', '特色小炒', '1', '0');
INSERT INTO `menu` VALUES ('4', '干锅（含米饭）', '1', '0');
INSERT INTO `menu` VALUES ('5', '砂锅（含饭）', '1', '0');
INSERT INTO `menu` VALUES ('6', '烫饭', '1', '0');
INSERT INTO `menu` VALUES ('7', '滋味炒饭', '1', '0');
INSERT INTO `menu` VALUES ('8', '面食', '1', '0');
INSERT INTO `menu` VALUES ('9', '包子粥', '1', '0');
INSERT INTO `menu` VALUES ('10', '饮料', '1', '0');
INSERT INTO `menu` VALUES ('11', '米饭', '1', '0');
INSERT INTO `menu` VALUES ('20', '烤肉', '1', '0');

-- ----------------------------
-- Table structure for order_address
-- ----------------------------
DROP TABLE IF EXISTS `order_address`;
CREATE TABLE `order_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动增长id',
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '订单id',
  `name` varchar(50) NOT NULL COMMENT '收件人名字',
  `mobile` varchar(20) NOT NULL COMMENT '电话',
  `gender` int(11) DEFAULT '1' COMMENT '1 - 男士 2 - 女士',
  `address` varchar(500) NOT NULL COMMENT '地址详情',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单地址表';

-- ----------------------------
-- Records of order_address
-- ----------------------------
INSERT INTO `order_address` VALUES ('1', '1526894585968138586', '张三', '17683116320', '1', '四川理工汇南校区5栋');
INSERT INTO `order_address` VALUES ('2', '1526894933660018040', '张三', '17683116320', '1', '四川理工汇南校区5栋');
INSERT INTO `order_address` VALUES ('3', '1527921083507545560', '张三', '17683116320', '1', '四川理工汇南校区5栋');
INSERT INTO `order_address` VALUES ('4', '1527930132488008594', '李四', '18828871539', '1', '四川理工汇南校区4栋');
INSERT INTO `order_address` VALUES ('5', '1527951491313771385', '余飞', '18828871539', '2', '四川省自贡市自流井区一对山商业城(汇东路) 四栋1010');
INSERT INTO `order_address` VALUES ('6', '1528011574727276485', '余飞', '18828871539', '2', '四川省自贡市自流井区一对山商业城(汇东路) 四栋1010');

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动增长id',
  `disk_id` int(11) NOT NULL COMMENT '菜品id',
  `order_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '订单id',
  `num` int(10) DEFAULT NULL COMMENT '商品购买数量',
  `title` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '商品标题',
  `price` float DEFAULT NULL COMMENT '商品单价',
  `dis_price` float DEFAULT NULL COMMENT '商品折扣价',
  `total_price` float DEFAULT NULL COMMENT '商品总金额',
  `img` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '商品图片地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES ('1', '12', '1526894585968138586', '1', '麻辣豆腐', '9', '0', '0', 'https://fuss10.elemecdn.com/a/ae/18dbb7fbd2fad45253f16a81708c6jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85');
INSERT INTO `order_item` VALUES ('2', '13', '1526894585968138586', '1', '麻辣血旺', '9', '0', '0', 'https://fuss10.elemecdn.com/f/25/3d7ac8880476ae918fed66f2578bbjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85');
INSERT INTO `order_item` VALUES ('3', '12', '1526894933660018040', '1', '麻辣豆腐', '9', '9', '9', 'https://fuss10.elemecdn.com/a/ae/18dbb7fbd2fad45253f16a81708c6jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85');
INSERT INTO `order_item` VALUES ('4', '13', '1526894933660018040', '1', '麻辣血旺', '9', '9', '9', 'https://fuss10.elemecdn.com/f/25/3d7ac8880476ae918fed66f2578bbjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85');
INSERT INTO `order_item` VALUES ('5', '97', '1527921083507545560', '1', '羊肉串', '5', '5', '5', 'https://canteentakeout.oss-cn-shenzhen.aliyuncs.com/pics/c9a51ec60bcd4653ac8776630f361cf8.jpg');
INSERT INTO `order_item` VALUES ('6', '13', '1527930132488008594', '1', '麻辣血旺', '9', '9', '9', 'https://fuss10.elemecdn.com/f/25/3d7ac8880476ae918fed66f2578bbjpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85');
INSERT INTO `order_item` VALUES ('7', '12', '1527951491313771385', '1', '麻辣豆腐', '9', '9', '9', 'https://fuss10.elemecdn.com/a/ae/18dbb7fbd2fad45253f16a81708c6jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85');
INSERT INTO `order_item` VALUES ('8', '2', '1527951491313771385', '1', '鱼香肉丝盖饭', '9', '9', '9', 'https://fuss10.elemecdn.com/0/91/62d9d2e47aa00b695f4aaad465286jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/85');

-- ----------------------------
-- Table structure for order_main
-- ----------------------------
DROP TABLE IF EXISTS `order_main`;
CREATE TABLE `order_main` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '订单id',
  `openid` varchar(35) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `total_money` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分',
  `status` int(10) DEFAULT NULL COMMENT '状态：1、未付款，2、已付款，3、已接单 ，4、已发货  5、交易成功，7、交易取消（未付款请款下取消）8、交易取消（付款情况下取消记得退款） ',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '订单更新时间',
  `payment_time` datetime DEFAULT NULL COMMENT '付款时间',
  `consign_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `remarks` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '买家留言',
  `is_evaluate` int(2) DEFAULT NULL COMMENT '买家是否已经评价1-未评价 2-评价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of order_main
-- ----------------------------
INSERT INTO `order_main` VALUES ('1526894933660018040', 'o3QX10JEZASEVKP0y94QnH8dKpv4', '18', '5', '2018-05-21 17:28:54', '2018-05-21 17:29:56', '2018-05-21 17:28:55', '2018-05-21 17:29:29', '2018-05-21 17:29:56', null, null, '2');
INSERT INTO `order_main` VALUES ('1527921083507545560', 'o3QX10JEZASEVKP0y94QnH8dKpv4', '5', '5', '2018-06-02 14:31:24', '2018-06-02 14:32:29', '2018-06-02 14:31:26', '2018-06-02 14:32:00', '2018-06-02 14:32:28', null, '超辣', '2');
INSERT INTO `order_main` VALUES ('1527930132488008594', 'o3QX10JEZASEVKP0y94QnH8dKpv4', '9', '5', '2018-06-02 17:02:12', '2018-06-02 17:04:05', '2018-06-02 17:02:14', '2018-06-02 17:03:34', '2018-06-02 17:04:05', null, '哈哈', '2');
INSERT INTO `order_main` VALUES ('1527951491313771385', 'o3QX10JEZASEVKP0y94QnH8dKpv4', '18', '8', '2018-06-02 22:58:11', '2018-06-02 22:58:12', '2018-06-02 22:58:12', null, null, '2018-06-02 23:30:33', '你好', '1');
INSERT INTO `order_main` VALUES ('1528011574727276485', 'o3QX10JEZASEVKP0y94QnH8dKpv4', '9', '8', '2018-06-03 15:39:35', '2018-06-03 15:40:05', '2018-06-03 15:40:05', null, null, '2018-06-03 16:13:58', '打火机大家', '1');
