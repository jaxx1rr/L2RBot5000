/*
  Copyright 2018 RR Design

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import org.sikuli.basics.Settings;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class L2RBot {

	private static final Logger logger = Logger.getLogger(L2RBot.class.getName());

	private static App myapp;

	private static Pattern AUTO_START_MYAPPS;
	private static Pattern AUTO_START_L2;
	private static Pattern AUTO_START_CLOSE;
	private static Pattern AUTO_START_PLAY;
	
	private static Pattern MQ_CLAIM_REWARD;
	private static Pattern MQ_ACCEPT_QUEST;
	private static Pattern MQ_SPOT_REVIVAL;
	private static Pattern MQ_DEATH_X;
	private static Pattern MQ_WALK_TOWARDS;
	private static Pattern MQ_QUEST_ARROWS;
	private static Pattern MQ_QUEST_SKIP;
	private static Pattern MQ_BOX_USE;
	private static Pattern MQ_CONTINUE;
	private static Pattern MQ_LOCKED;
	private static Pattern MQ_LOCKED2;
	
	private static Pattern QUESTLOG_START;
	private static Pattern WEEKLY_QUESTS;
	private static Pattern WEEKLY_DONE;
	private static Pattern WEEKLY_QUEST_COMPLETE;
	private static Pattern WEEKLY_START_QUEST;
	private static Pattern WEEKLY_GO_NOW;
	private static Pattern WEEKLY_WALK;
	private static Pattern WEEKLY_COMPLETE;
	private static Pattern PAGE_IS_DAILY_QUESTS;
	
	private static Pattern DAILY_CLAIM_REWARD;
	private static Pattern DAILY_COMPLETE_W_COIN;
	private static Pattern DAILY_R;
	private static Pattern DAILY_S;
	private static Pattern DAILY_REFRESH;
	private static Pattern DAILY_DONE;
	private static Pattern DAILY_QUESTS;
	private static Pattern DAILY_AVAILABLE;
	private static Pattern DAILY_REWARD1;
	private static Pattern DAILY_REWARD2;
	private static Pattern DAILY_COMPLETED;
	
	private static Pattern SUBQ_AVAIL;
	private static Pattern SUBQ_NOTAVAIL;
	private static Pattern SUBQ_A;
	private static Pattern SUBQ_B;
	private static Pattern SUBQ_X;
	private static Pattern SUBQ_FF;
	private static Pattern SUBQ_OK;
	private static Pattern SUBQ_START;
	private static Pattern SUBQ_CONT;
	private static Pattern SUBQ_SHOP;
	private static Pattern SUBQ_SHOP2;
	private static Pattern SUBQ_SCROLL;
	private static Pattern SUBQ_BUYSCROLL;
	private static Pattern SUBQ_BUYSCROLL_BUY;
	private static Pattern SUBQ_SOLD_OUT;
	private static Pattern SUBQ_RESET;
	private static Pattern SUBQ_RESET_COUNT50;
	private static Pattern SUBQ_RESET_RESET;
	private static Pattern SUBQ_RESET_CANCEL;
	private static Pattern EXIT;
	
	private static Pattern DAILY_ACT;
	private static Pattern DA_SCREEN;
	private static Pattern DA_CLAIM_REWARD;
	
	private static Pattern DA_REW1;
	private static Pattern DA_REW2;
	private static Pattern DA_REW3;
	
	private static Pattern DA_FREE_DRAW;
	private static Pattern DA_BAD_IMAGE_X;
	private static Pattern DA_FREE_DRAW_FP;
	private static Pattern DA_FREE_BOX;
	private static Pattern DA_FREE_BOX_OPEN;
	private static Pattern DA_FREE_BOX_OPEN_OK;
	
	private static Pattern DA_TOI;
	private static Pattern DA_TOI_AC;
	private static Pattern DA_TOI_AC_OK;
	
	private static Pattern DA_FRIENDS;
	private static Pattern DA_FRIENDS_GA;
	private static Pattern DA_FRIENDS_CA;
	private static Pattern DA_FRIENDS_OK;
	
	private static Pattern DA_DD;
	private static Pattern DA_DD_ENTER;
	private static Pattern DA_DD_OK;
	
	private static Pattern DA_CLAN;
	private static Pattern DA_CLAN_DONATION;
	private static Pattern DA_CLAN_CHECKIN;
	private static Pattern DA_CLAN_CHECKIN_REWARD;
	private static Pattern DA_CLAN_CHECKIN_REWARD_OK;
	
	private static Pattern DA_CLAN_MEMBERS;
	private static Pattern DA_CLAN_MEMBERS_GA;
	private static Pattern DA_CLAN_MEMBERS_CA;
	private static Pattern DA_CLAN_MEMBERS_OK;
	private static Pattern DA_CLAN_MEMBERS_INFO;
	
	private static Pattern DA_CLAN_DONATEBTN;
	private static Pattern DA_CLAN_DONATEBTN2;
	private static Pattern DA_CLAN_DONATE_SLIDER;
	private static Pattern DA_CLAN_DONATE_PLUS;
	private static Pattern DA_CLAN_DONATE_SUBMIT1;
	private static Pattern DA_CLAN_DONATE_SUBMIT2;
	private static Pattern DA_CLAN_DONATE_SUBMIT3;
	private static Pattern DA_CLAN_DONATE_TAB2;
	private static Pattern DA_CLAN_DONATE_TAB3;
	private static Pattern DA_CLAN_NE;
	private static Pattern DA_CLAN_NEX;
	private static Pattern DA_CLAN_DONATE_REWARD;
	
	private static Pattern DA_CLAN_SHOP;
	private static Pattern DA_CLAN_SHOP_GX;
	private static Pattern DA_CLAN_SHOP_GX_BUY;
	private static Pattern DA_CLAN_SHOP_GX_BUY_OK;
	private static Pattern DA_CLAN_SHOP_GX_BUY_KO;
	private static Pattern DA_CLAN_SHOP_GX_BUY_KO_X;
	
	private static Pattern DA_ARENA;
	private static Pattern DA_ARENA_CLAIM_REWARD;
	private static Pattern DA_ARENA_CLAIM_REWARD_OK;
	private static Pattern DA_ARENA_CP1;
	private static Pattern DA_ARENA_CP2;
	private static Pattern DA_ARENA_CP3;
	private static Pattern DA_ARENA_CP4;
	private static Pattern DA_ARENA_CP5;
	private static Pattern DA_ARENA_REFRESH;
	private static Pattern DA_ARENA_OK;
	private static Pattern DA_ARENA_RANK_INCREASED;
	
	private static Pattern DA_COMPLETE;
	
	private static Pattern DA_ELITE_DUNGEON;
	private static Pattern DA_ELITE_DUNGEON_CRUMA3;
	private static Pattern DA_ELITE_DUNGEON_IVORY1;
	private static Pattern DA_ELITE_DUNGEON_IVORY2;
	private static Pattern DA_ELITE_DUNGEON_IVORY3;
	private static Pattern DA_ELITE_DUNGEON_FOS_CANOPY;
	private static Pattern DA_ELITE_DUNGEON_FOS_UNDER;
	private static Pattern DA_ELITE_DUNGEON_AUTOCLEAR;
	private static Pattern DA_ELITE_DUNGEON_AUTOCLEAR_OK;
	private static Pattern DA_ELITE_NOT_ENOUGH_DIAMONDS;
	private static Pattern DA_ELITE_NOT_ENOUGH_DIAMONDS_X;
	
	private static Pattern DA_HERBS;
	private static Pattern DA_HERBS_ENTER;
	private static Pattern DA_MAP_X;
	private static Pattern DA_HERBS_GATHER_BTN;
	private static Pattern DA_HERBS_COMPLETE;
	private static Pattern DA_DUNGEON_EXIT_CONF;
	private static Pattern DA_DUNGEON_OK;
	
	private static Pattern DA_TEMPLE;
	private static Pattern DA_TEMPLE_PARTY;
	private static Pattern DA_TEMPLE_INPARTY;
	private static Pattern DA_TEMPLE_INPARTY_OK;
	private static Pattern DA_TEMPLE_OK;
	
	private static Pattern DA_VAULT;
	private static Pattern DA_VAULT_AC;
	private static Pattern DA_VAULT_AC_GO;
	private static Pattern DA_VAULT_AC_OK;
	private static Pattern DA_VAULT_AC_BACK;
	private static Pattern DA_VAULT_ENTER;
	private static Pattern DA_VAULT_CONFIRM;
	private static Pattern DA_VAULT_FINISHED_OK;
	
	private static Pattern DA_TRIALS;
	private static Pattern DA_TRIALS_AC;
	private static Pattern DA_TRIALS_AC_GO;
	private static Pattern DA_TRIALS_AC_OK;
	private static Pattern DA_TRIALS_AC_BACK;
	private static Pattern DA_TRIALS_ENTER;
	private static Pattern DA_TRIALS_CONFIRM;
	private static Pattern DA_TRIALS_FINISHED_OK;
	private static Pattern DA_TRIALS_DENIED;
	private static Pattern DA_TRIALS_OK;
	
	private static Pattern DA_CIRCLE;
	private static Pattern DA_CIRCLE_PARTY;
	private static Pattern DA_CIRCLE_INPARTY;
	private static Pattern DA_CIRCLE_INPARTY_OK;
	private static Pattern DA_CIRCLE_OK;
	
	private static Pattern DA_FREE_HG;
	private static Pattern DA_FREE_HG_BOX;
	private static Pattern DA_FREE_HG_BOX_OPEN;
	private static Pattern DA_FREE_HG_BOX_OPEN_OK;
	
	private static Pattern INV_SCREEN;
	private static Pattern INV_POTION;
	private static Pattern INV_RED_DOT;
	private static Pattern INV_RED_DOT_NEW;
	private static Pattern INV_S1_USE;
	private static Pattern INV_S2_USE;
	private static Pattern INV_S2_CHOICE_TICKET;
	private static Pattern INV_S2_CHOICE_STONE;
	private static Pattern INV_S2_CHOICE_STONE2;
	private static Pattern INV_S2_CHOICE_VARNISH;
	private static Pattern INV_S2_CHOICE_UPGRADE;
	private static Pattern INV_S2_CHOICE_ENHANCE;
	
	private static Pattern INV_S2_NEXT;
	private static Pattern INV_S3_OK;
	private static Pattern INV_S4_OK;
	private static Pattern INV_SLIDER;
	private static Pattern INV_PLUS;
	private static Pattern INV_MORE;
	
	private static Pattern EXIT_FROM_MENU;
	private static Pattern IS_IN_MENU;
	private static Pattern GO_BACK;
	private static Pattern PARTY_20;
	
	private static Pattern BTN_CLAN;
	private static Pattern BTN_CLAN_HALL;
	private static Pattern CH_MERCH_GIFT;
	private static Pattern CH_RELIC_CHECK;
	private static Pattern CH_RELIC_CHECK_OK;

	private static Pattern CH_RELIC_BOX1;
	private static Pattern CH_RELIC_BOX2;
	private static Pattern CH_RELIC_BOX3;
	private static Pattern CH_RELIC_REGISTER;
	
	private static Pattern BTN_CHALLENGES;
	private static Pattern BTN_CHIEVES;
	private static Pattern BTN_CODEX;
	
	private static Pattern CHA_CLAIM;
	private static Pattern CHA_CLAIM_SMALL;
	private static Pattern CHA_RED_DOT;
	private static Pattern CHA_OK;

	private static Pattern CHA_CODEX_LISTALL;
	private static Pattern CHA_CODEX_OK;
	private static Pattern CHA_CODEX_REG_OK;
	
	private static Pattern MAIL_RED_DOT_MAIN;
	private static Pattern MAIL_RED_DOT;
	private static Pattern MAIL_COLLECT_ALL;
	private static Pattern MAIL_SCREEN;
	private static Pattern MAIL_NEXT;
	private static Pattern MAIL_OK;
	
	private static Pattern BTN_DUNGEON;
	private static Pattern BTN_DUNGEON_NORMAL;
	private static Pattern BTN_DUNGEON_ELITE;
	private static Pattern DUNGEON_ENTER;
	private static Pattern DUNGEON_ENTER_CONFIRM;
	private static Pattern DUNGEON_ENTER_CONFIRM_CONFIRM;
	
	private static Pattern DUNGEON_PARTY_MANAGE;
	private static Pattern DUNGEON_PARTY_DISBAND;
	private static Pattern DUNGEON_PARTY_DISBAND_OK;
	
	private static Pattern DUNGEON_PARTY_5PERCENT;
	private static Pattern DUNGEON_PARTY_SEE;
	private static Pattern DUNGEON_PARTY_LEAVE;
	private static Pattern DUNGEON_PARTY_LEAVE_OK;
	
	private static Pattern DUNGEON_PARTY_TAB;
	private static Pattern DUNGEON_PARTY_SEARCH;
	private static Pattern DUNGEON_PARTY_SEARCH_MANUAL;
	private static Pattern DUNGEON_PARTY_JOIN;

	private static Pattern QUESTS_TAB;
	private static Pattern WORLD_MAP;

	private static Pattern POTIONS_20;
	private static Pattern POTIONS_15;
	private static Pattern POTIONS_10;
	private static Pattern POTIONS_0;
	private static Pattern POTIONS_BUY;
	private static Pattern POTIONS_NO_ADENA;
	private static Pattern POTIONS_NO_ADENA_X;
	
	private static Pattern BTN_CONSUMABLES;
	
	private static Pattern UPDATE_OK;
	private static Pattern REWARD_X;

	private static Pattern INVSELL_BULK;
	private static Pattern INVSELL_SELL;
	private static Pattern INVSELL_SELL_CONF;
	private static Pattern INVSELL_SELL_OK;

	
	private static Pattern TV_OK = new Pattern("images\\tv_ok.png");
	
	
	private static final String STR_AUTO_START_MYAPPS = "auto_start_myapps.png";
	private static final String STR_AUTO_START_L2 = "auto_start_l2.png";
	private static final String STR_AUTO_START_CLOSE = "auto_start_close.png";
	private static final String STR_AUTO_START_PLAY = "auto_start_play.png";
	
	private static final String STR_MQ_CLAIM_REWARD = "mq_claim_reward.png";
	private static final String STR_MQ_ACCEPT_QUEST = "mq_accept_quest.png";
	private static final String STR_MQ_SPOT_REVIVAL = "mq_spot_revival.png";
	private static final String STR_MQ_DEATH_X = "mq_death_x.png";
	private static final String STR_MQ_WALK_TOWARDS = "mq_walk_towards.png";
	private static final String STR_MQ_QUEST_ARROWS = "mq_quest_arrows.png";
	private static final String STR_MQ_QUEST_SKIP = "mq_quest_skip.png";
	private static final String STR_MQ_BOX_USE = "mq_box_use.png";
	private static final String STR_MQ_CONTINUE = "mq_continue.png";
	private static final String STR_MQ_LOCKED = "mq_locked.png";
	private static final String STR_MQ_LOCKED2 = "mq_locked2.png";
	
	private static final String STR_QUESTLOG_START = "questlog_start.png";
	private static final String STR_WEEKLY_QUESTS = "weekly_quests.png";
	private static final String STR_WEEKLY_DONE = "weekly_done.png";
	private static final String STR_WEEKLY_QUEST_COMPLETE = "weekly_quest_complete.png";
	private static final String STR_WEEKLY_START_QUEST = "weekly_start_quest.png";
	private static final String STR_WEEKLY_GO_NOW = "weekly_go_now.png";
	private static final String STR_WEEKLY_WALK = "weekly_walk.png";
	private static final String STR_WEEKLY_COMPLETE = "weekly_complete.png";
	private static final String STR_PAGE_IS_DAILY_QUESTS = "page_is_daily_quests.png";
	
	private static final String STR_DAILY_CLAIM_REWARD = "daily_claim_reward.png";
	private static final String STR_DAILY_COMPLETE_W_COIN = "daily_complete_w_coin.png";
	private static final String STR_DAILY_R = "daily_r.png";
	private static final String STR_DAILY_S = "daily_s.png";
	private static final String STR_DAILY_REFRESH = "daily_refresh.png";
	private static final String STR_DAILY_DONE = "daily_done.png";
	private static final String STR_DAILY_QUESTS = "daily_quests.png";
	private static final String STR_DAILY_AVAILABLE = "daily_available.png";
	private static final String STR_DAILY_REWARD1 = "daily_reward1.png";
	private static final String STR_DAILY_REWARD2 = "daily_reward2.png";
	private static final String STR_DAILY_COMPLETED = "daily_completed.png";
	
	private static final String STR_SUBQ_AVAIL = "subquests_available.png";
	private static final String STR_SUBQ_NOTAVAIL = "subquests_notavail.png";
	private static final String STR_SUBQ_A = "subquests_a.png";
	private static final String STR_SUBQ_B = "subquests_b.png";
	private static final String STR_SUBQ_X = "subquests_x.png";
	private static final String STR_SUBQ_FF = "subquests_fulfill.png";
	private static final String STR_SUBQ_OK = "subquests_ok.png";
	private static final String STR_SUBQ_START = "subquests_start.png";
	private static final String STR_SUBQ_CONT = "subquests_sub.png";
	private static final String STR_SUBQ_SHOP = "subquests_shop.png";
	private static final String STR_SUBQ_SHOP2 = "subquests_shop2.png";
	private static final String STR_SUBQ_SCROLL = "subquests_scroll.png";
	private static final String STR_SUBQ_BUYSCROLL = "subquests_buyscroll.png";
	private static final String STR_SUBQ_BUYSCROLL_BUY = "subquests_buyscroll_buy.png";
	private static final String STR_SUBQ_SOLD_OUT = "subquests_sold_out.png";
	private static final String STR_SUBQ_RESET = "subquests_reset.png";
	private static final String STR_SUBQ_RESET_COUNT50 = "subquests_reset_count50.png";
	private static final String STR_SUBQ_RESET_RESET = "subquests_reset_reset.png";
	private static final String STR_SUBQ_RESET_CANCEL = "subquests_reset_cancel.png";
	
	private static final String STR_EXIT = "exit.png";
	
	private static final String STR_DAILY_ACT = "daily_activities.png";
	private static final String STR_DA_SCREEN = "da_screen.png";
	private static final String STR_DA_CLAIM_REWARD = "da_claim_reward.png";
	
	private static final String STR_DA_REW1 = "da_rew1.png";
	private static final String STR_DA_REW2 = "da_rew2.png";
	private static final String STR_DA_REW3 = "da_rew3.png";
	
	private static final String STR_DA_FREE_DRAW = "da_free_draw.png";
	private static final String STR_DA_BAD_IMAGE_X = "da_bad_image_x.png";
	private static final String STR_DA_FREE_DRAW_FP = "da_free_draw_fp.png";
	private static final String STR_DA_FREE_BOX = "da_free_equip_box.png";
	private static final String STR_DA_FREE_BOX_OPEN = "da_free_equip_box_open.png";
	private static final String STR_DA_FREE_BOX_OPEN_OK = "da_free_equip_box_open_ok.png";
	
	private static final String STR_DA_TOI = "da_toi.png";
	private static final String STR_DA_TOI_AC = "da_toi_autoclear.png";
	private static final String STR_DA_TOI_AC_OK = "da_toi_ac_ok.png";
	
	private static final String STR_DA_FRIENDS = "da_friends.png";
	private static final String STR_DA_FRIENDS_GA = "da_friends_ga.png";
	private static final String STR_DA_FRIENDS_CA = "da_friends_ca.png";
	private static final String STR_DA_FRIENDS_OK = "da_friends_ok.png";
	
	private static final String STR_DA_DD = "da_dd.png";
	private static final String STR_DA_DD_ENTER = "da_dd_enter.png";
	private static final String STR_DA_DD_OK = "da_dd_ok.png";
	
	private static final String STR_DA_CLAN = "da_clan.png";
	private static final String STR_DA_CLAN_DONATION = "da_clan_donation.png";
	private static final String STR_DA_CLAN_CHECKIN = "da_clan_checkin.png";
	private static final String STR_DA_CLAN_CHECKIN_REWARD = "da_clan_ci_rew.png";
	private static final String STR_DA_CLAN_CHECKIN_REWARD_OK = "da_clan_ci_rew_ok.png";
	
	private static final String STR_DA_CLAN_MEMBERS = "da_clan_members.png";
	private static final String STR_DA_CLAN_MEMBERS_GA = "da_clan_members_ga.png";
	private static final String STR_DA_CLAN_MEMBERS_CA = "da_clan_members_ca.png";
	private static final String STR_DA_CLAN_MEMBERS_OK = "da_clan_members_ok.png";
	private static final String STR_DA_CLAN_MEMBERS_INFO = "da_clan_members_info.png";
	
	private static final String STR_DA_CLAN_DONATEBTN = "da_clan_donate.png";
	private static final String STR_DA_CLAN_DONATEBTN2 = "da_clan_donate2.png";
	private static final String STR_DA_CLAN_DONATE_SLIDER = "da_clan_donate_slider.png";
	private static final String STR_DA_CLAN_DONATE_PLUS = "da_clan_donate_plus.png";
	private static final String STR_DA_CLAN_DONATE_SUBMIT1 = "da_clan_donate_submit1.png";
	private static final String STR_DA_CLAN_DONATE_SUBMIT2 = "da_clan_donate_submit2.png";
	private static final String STR_DA_CLAN_DONATE_SUBMIT3 = "da_clan_donate_submit3.png";
	private static final String STR_DA_CLAN_DONATE_TAB2 = "da_clan_donate_tab2.png";
	private static final String STR_DA_CLAN_DONATE_TAB3 = "da_clan_donate_tab3.png";
	private static final String STR_DA_CLAN_DONATE_REWARD = "da_clan_donate_reward.png";
	private static final String STR_DA_CLAN_NE = "da_clan_ne.png";
	private static final String STR_DA_CLAN_NEX = "da_clan_nex.png";
	
	private static final String STR_DA_CLAN_SHOP = "da_clan_shop.png";
	private static final String STR_DA_CLAN_SHOP_GX = "da_clan_shop_gexp.png";
	private static final String STR_DA_CLAN_SHOP_GX_BUY = "da_clan_shop_gexp_buy.png";
	private static final String STR_DA_CLAN_SHOP_GX_BUY_OK = "da_clan_shop_gexp_buy_ok.png";
	private static final String STR_DA_CLAN_SHOP_GX_BUY_KO = "da_clan_shop_gexp_buy_ko.png";
	private static final String STR_DA_CLAN_SHOP_GX_BUY_KO_X = "da_clan_shop_gexp_buy_ko_x.png";
	
	private static final String STR_DA_ARENA = "da_arena.png";
	private static final String STR_DA_ARENA_CLAIM_REWARD = "da_arena_claim_reward.png";
	private static final String STR_DA_ARENA_CLAIM_REWARD_OK = "da_arena_claim_reward_ok.png";
	private static final String STR_DA_ARENA_CP1 = "da_arena_cp1.png";
	private static final String STR_DA_ARENA_CP2 = "da_arena_cp2.png";
	private static final String STR_DA_ARENA_CP3 = "da_arena_cp3.png";
	private static final String STR_DA_ARENA_CP4 = "da_arena_cp4.png";
	private static final String STR_DA_ARENA_CP5 = "da_arena_cp5.png";
	private static final String STR_DA_ARENA_REFRESH = "da_arena_refresh.png";
	private static final String STR_DA_ARENA_OK = "da_arena_ok.png";
	private static final String STR_DA_ARENA_RANK_INCREASED = "da_arena_rank_increased.png";
	
	private static final String STR_DA_COMPLETE = "da_complete.png";
	
	private static final String STR_DA_ELITE_DUNGEON = "da_elited.png";
	private static final String STR_DA_ELITE_DUNGEON_CRUMA3 = "da_elited_cruma3.png";
	private static final String STR_DA_ELITE_DUNGEON_IVORY1 = "da_elited_ivory1.png";
	private static final String STR_DA_ELITE_DUNGEON_IVORY2 = "da_elited_ivory2.png";
	private static final String STR_DA_ELITE_DUNGEON_IVORY3 = "da_elited_ivory3.png";
	private static final String STR_DA_ELITE_DUNGEON_FOS_CANOPY = "da_elited_fos_canopy.png";
	private static final String STR_DA_ELITE_DUNGEON_FOS_UNDER = "da_elited_fos_under.png";
	private static final String STR_DA_ELITE_DUNGEON_AUTOCLEAR = "da_elited_autoclear.png";
	private static final String STR_DA_ELITE_DUNGEON_AUTOCLEAR_OK = "da_elited_autoclear_ok.png";
	private static final String STR_DA_ELITE_NOT_ENOUGH_DIAMONDS = "da_elite_not_enough_diamonds.png";
	private static final String STR_DA_ELITE_NOT_ENOUGH_DIAMONDS_X = "da_elite_not_enough_diamonds_x.png";
	
	private static final String STR_DA_HERBS = "da_herbs.png";
	private static final String STR_DA_HERBS_ENTER = "da_herbs_enter.png";
	private static final String STR_DA_MAP_X = "da_herbs_map_x.png";
	private static final String STR_DA_HERBS_GATHER_BTN = "da_herbs_gather_btn.png";
	private static final String STR_DA_HERBS_COMPLETE = "da_herbs_complete.png";
	private static final String STR_DA_DUNGEON_EXIT_CONF = "da_herbs_exit_conf.png";
	private static final String STR_DA_DUNGEON_OK = "da_dungeon_ok.png";
	
	private static final String STR_DA_TEMPLE = "da_temple.png";
	private static final String STR_DA_TEMPLE_PARTY = "da_temple_party.png";
	private static final String STR_DA_TEMPLE_INPARTY = "da_temple_inparty.png";
	private static final String STR_DA_TEMPLE_INPARTY_OK = "da_temple_inparty_ok.png";
	private static final String STR_DA_TEMPLE_OK = "da_temple_ok.png";

	private static final String STR_DA_VAULT = "da_vault.png";
	private static final String STR_DA_VAULT_AC = "da_vault_ac.png";
	private static final String STR_DA_VAULT_AC_GO = "da_vault_ac_go.png";
	private static final String STR_DA_VAULT_AC_OK = "da_vault_ac_ok.png";
	private static final String STR_DA_VAULT_AC_BACK = "da_vault_ac_back.png";
	private static final String STR_DA_VAULT_ENTER = "da_vault_enter.png";
	private static final String STR_DA_VAULT_CONFIRM = "da_trials_confirm.png";
	private static final String STR_DA_VAULT_FINISHED_OK = "da_vault_finished_ok.png";
	
	private static final String STR_DA_TRIALS = "da_trials.png";
	private static final String STR_DA_TRIALS_AC = "da_vault_ac.png";
	private static final String STR_DA_TRIALS_AC_GO = "da_trials_ac_go.png";
	private static final String STR_DA_TRIALS_AC_OK = "da_vault_ac_ok.png";
	private static final String STR_DA_TRIALS_AC_BACK = "da_vault_ac_back.png";
	private static final String STR_DA_TRIALS_ENTER = "da_vault_enter.png";
	private static final String STR_DA_TRIALS_CONFIRM = "da_trials_confirm.png";
	private static final String STR_DA_TRIALS_FINISHED_OK = "da_vault_finished_ok.png";
	private static final String STR_DA_TRIALS_DENIED = "da_trials_denied.png";
	private static final String STR_DA_TRIALS_OK = "da_trials_ok.png";

	private static final String STR_DA_CIRCLE = "da_circle.png";
	private static final String STR_DA_CIRCLE_PARTY = "da_temple_party.png";
	private static final String STR_DA_CIRCLE_INPARTY = "da_temple_inparty.png";
	private static final String STR_DA_CIRCLE_INPARTY_OK = "da_temple_inparty_ok.png";
	private static final String STR_DA_CIRCLE_OK = "da_temple_ok.png";

	private static final String STR_DA_FREE_HG = "da_free_hg.png";
	private static final String STR_DA_FREE_HG_BOX = "da_free_hg_box.png";
	private static final String STR_DA_FREE_HG_BOX_OPEN = "da_free_equip_box_open.png";
	private static final String STR_DA_FREE_HG_BOX_OPEN_OK = "da_free_equip_box_open_ok.png";
	
	private static final String STR_INV_SCREEN = "inv_screen.png";
	private static final String STR_INV_POTION = "inv_potion.png";
	private static final String STR_INV_RED_DOT = "inv_red_dot.png";
	private static final String STR_INV_RED_DOT_NEW = "inv_red_dot_new.png";
	private static final String STR_INV_S1_USE = "inv_s1_use.png";
	private static final String STR_INV_S2_USE = "inv_s2_use.png";
	private static final String STR_INV_S2_CHOICE_TICKET = "inv_s2_choice_ticket.png";
	private static final String STR_INV_S2_CHOICE_STONE = "inv_s2_choice_stone.png";
	private static final String STR_INV_S2_CHOICE_STONE2 = "inv_s2_choice_stone2.png";
	private static final String STR_INV_S2_CHOICE_VARNISH = "inv_s2_choice_varnish.png";
	private static final String STR_INV_S2_CHOICE_UPGRADE = "inv_s2_choice_upgrade.png";
	private static final String STR_INV_S2_CHOICE_ENHANCE = "inv_s2_choice_enhance.png";
	
	private static final String STR_INV_S2_NEXT = "inv_s2_next.png";
	private static final String STR_INV_S3_OK = "inv_s3_ok.png";
	private static final String STR_INV_S4_OK = "inv_s4_ok.png";
	private static final String STR_INV_SLIDER = "inv_slider.png";
	private static final String STR_INV_PLUS = "inv_plus.png";
	private static final String STR_INV_MORE = "inv_more.png";
	
	private static final String STR_EXIT_FROM_MENU = "exit_from_menu.png";
	private static final String STR_IS_IN_MENU = "is_in_menu.png";
	private static final String STR_GO_BACK = "go_back.png";
	private static final String STR_PARTY_20 = "party_20.png";
	
	private static final String STR_BTN_CLAN = "btn_clan.png";
	private static final String STR_BTN_CLAN_HALL = "btn_clan_hall.png";
	private static final String STR_CH_MERCH_GIFT = "ch_merch_gift.png";
	private static final String STR_CH_RELIC_CHECK = "ch_relic_check.png";
	private static final String STR_CH_RELIC_CHECK_OK = "ch_relic_check_ok.png";
	private static final String STR_CH_RELIC_BOX1 = "ch_relic_box1.png";
	private static final String STR_CH_RELIC_BOX2 = "ch_relic_box2.png";
	private static final String STR_CH_RELIC_BOX3 = "ch_relic_box3.png";
	private static final String STR_CH_RELIC_REGISTER = "ch_relic_register.png";
	
	private static final String STR_BTN_CHALLENGES = "btn_challenges.png";
	private static final String STR_BTN_CHIEVES = "btn_chieves.png";
	private static final String STR_BTN_CODEX = "btn_codex.png";
	
	private static final String STR_CHA_CLAIM = "cha_claim.png";
	private static final String STR_CHA_CLAIM_SMALL = "cha_claim_small.png";
	private static final String STR_CHA_RED_DOT = "cha_red_dot.png";
	private static final String STR_CHA_OK = "cha_ok.png";

	private static final String STR_CHA_CODEX_LISTALL = "cha_codex_listall.png";
	private static final String STR_CHA_CODEX_OK = "cha_codex_ok.png";
	private static final String STR_CHA_CODEX_REG_OK = "cha_codex_reg_ok.png";
	
	private static final String STR_MAIL_RED_DOT_MAIN = "mail_red_dot_main.png";
	private static final String STR_MAIL_RED_DOT = "mail_red_dot.png";
	private static final String STR_MAIL_COLLECT_ALL = "mail_collect_all.png";
	private static final String STR_MAIL_SCREEN = "mail_screen.png";
	private static final String STR_MAIL_NEXT = "mail_next.png";
	private static final String STR_MAIL_OK = "mail_ok.png";
	
	private static final String STR_BTN_DUNGEON = "btn_dungeon.png";
	private static final String STR_BTN_DUNGEON_NORMAL = "btn_dungeon_normal.png";
	private static final String STR_BTN_DUNGEON_ELITE = "btn_dungeon_elite.png";
	private static final String STR_DUNGEON_ENTER = "dungeon_enter.png";
	private static final String STR_DUNGEON_ENTER_CONFIRM = "dungeon_enter_confirm.png";
	private static final String STR_DUNGEON_ENTER_CONFIRM_CONFIRM = "dungeon_enter_confirm_confirm.png";
	
	private static final String STR_DUNGEON_PARTY_MANAGE = "dungeon_party_manage.png";
	private static final String STR_DUNGEON_PARTY_DISBAND = "dungeon_party_disband.png";
	private static final String STR_DUNGEON_PARTY_DISBAND_OK = "dungeon_party_disband_ok.png";
	
	private static final String STR_DUNGEON_PARTY_5PERCENT = "dungeon_party_5percent.png";
	private static final String STR_DUNGEON_PARTY_SEE = "dungeon_party_see.png";
	private static final String STR_DUNGEON_PARTY_LEAVE = "dungeon_party_leave.png";
	private static final String STR_DUNGEON_PARTY_LEAVE_OK = "dungeon_party_leave_ok.png";
	
	private static final String STR_DUNGEON_PARTY_TAB = "dungeon_party_tab.png";
	private static final String STR_DUNGEON_PARTY_SEARCH = "dungeon_party_search.png";
	private static final String STR_DUNGEON_PARTY_SEARCH_MANUAL = "dungeon_party_search_manual.png";
	private static final String STR_DUNGEON_PARTY_JOIN = "dungeon_party_join.png";

	private static final String STR_QUESTS_TAB = "quests_tab.png";
	private static final String STR_WORLD_MAP = "world_map.png";

	private static final String STR_POTIONS_20 = "potions_20.png";
	private static final String STR_POTIONS_15 = "potions_15.png";
	private static final String STR_POTIONS_10 = "potions_10.png";
	private static final String STR_POTIONS_0 = "potions_0.png";
	private static final String STR_POTIONS_BUY = "potions_buy.png";
	private static final String STR_POTIONS_NO_ADENA = "potions_no_adena.png";
	private static final String STR_POTIONS_NO_ADENA_X = "potions_no_adena_x.png";
	
	private static final String STR_CONSUMABLES = "btn_consumables.png";
	
	private static final String STR_UPDATE_OK = "update_ok.png";
	private static final String STR_REWARD_X = "reward_x.png";
	
	private static final String STR_INVSELL_BULK = "invsell_bulk.png";
	private static final String STR_INVSELL_SELL = "invsell_sell.png";
	private static final String STR_INVSELL_SELL_CONF = "invsell_sell_conf.png";
	private static final String STR_INVSELL_SELL_OK = "invsell_sell_ok.png";
	
	private static Pattern DA_ELITE_DUNGEON_CURRENT_BTN;
	private static Pattern DUNGEON_RUN_CURRENT_BTN;
	
	private static final JLabel label_map_image = new JLabel("");

	private static BufferedImage MAP_IMAGE = null;
	private static BufferedImage MAP_X_IMAGE = null;
	
	private static final int mapsize = 950;
	private static int map_x_diff;
	private static int map_y_diff;
	private static final int map_x_half = 16;

	private static final int herbs_map_diff_x = 216;
	private static final int herbs_map_diff_y = 154;

	private static final int dungeon_map_cruma3_diff_x = 264;
	private static final int dungeon_map_cruma3_diff_y = 286;
	private static final int dungeon_map_ivory1_diff_x = 244;
	private static final int dungeon_map_ivory1_diff_y = 206;
	private static final int dungeon_map_ivory2_diff_x = 214;
	private static final int dungeon_map_ivory2_diff_y = 158;
	private static final int dungeon_map_ivory3_diff_x = 220;
	private static final int dungeon_map_ivory3_diff_y = 164;
	private static final int dungeon_map_foscan_diff_x = 218;
	private static final int dungeon_map_foscan_diff_y = 160;
	private static final int dungeon_map_fosund_diff_x = 0;
	private static final int dungeon_map_fosund_diff_y = 0;

	private static final JTextArea talog = new JTextArea(24, 31);
	
	private static final JLabel infoBox = new JLabel("");
	
	private static final JButton btn_main_quest = new JButton("MAIN QUEST");
	private static final JButton btn_weekly = new JButton("WEEKLY");
	private static final JButton btn_subquests = new JButton("SUBQUEST");
	private static final JButton btn_da = new JButton("DA");
	private static final JButton btn_AUTO = new JButton("AUTO");
	private static final JButton btn_ch_stuff = new JButton("CH STUFF");
	private static final JButton btn_challenges = new JButton("CHLG.");
	private static final JButton btn_mail = new JButton("MAIL");
	private static final JButton btn_inv = new JButton("INV");
	private static final JButton btn_dr = new JButton("DUNGEON RUN");
	private static final JButton btn_dr_noinit = new JButton("DR CONT");
	private static final JButton btn_settings = new JButton("SETTINGS");
	
	private static final JButton btn_stop = new JButton("STOP");
	private static final JButton btn_skip = new JButton("SKIP");
	private static final JButton btn_exit = new JButton("[X]");
	
	private static final JCheckBox cb_DA_FREE_DRAW = new JCheckBox("DA - Free Draw");
	private static final JCheckBox cb_DA_TOI = new JCheckBox("DA - Tower of Insolence Auto-Clear");
	private static final JCheckBox cb_DA_FRIENDS = new JCheckBox("DA - Greet Friends");
	private static final JCheckBox cb_DA_DD = new JCheckBox("DA - Daily Dungeon");
	private static final JCheckBox cb_DA_CLAN_DONATION = new JCheckBox("DA - CLAN [Donation - 75k]");
	private static final JCheckBox cb_DA_CLAN_SHOP = new JCheckBox("DA - CLAN [Shop - greater XP]");
	private static final JCheckBox cb_DA_CLAN_CHECKIN = new JCheckBox("DA - CLAN [Check In]");
	private static final JCheckBox cb_DA_CLAN_MEMBERS = new JCheckBox("DA - CLAN [Greet Members]");
	private static final JCheckBox cb_DA_ARENA = new JCheckBox("DA - Arena Fights x5");
	private static final JCheckBox cb_DA_ELITE_DUNGEON = new JCheckBox("DA - Elite Dungeon Auto [50 GEMS!]");
	private static final JCheckBox cb_DA_HERBS = new JCheckBox("DA - Gather Herbs");
	private static final JCheckBox cb_DA_FREE_HG = new JCheckBox("DA - High-grade draw");
	private static final JCheckBox cb_DA_TEMPLE = new JCheckBox("DA - Temple Guardian");
	private static final JCheckBox cb_DA_VAULT = new JCheckBox("DA - Adena Vault");
	private static final JCheckBox cb_DA_TRIALS = new JCheckBox("DA - Trials of Experience");
	private static final JCheckBox cb_DA_CIRCLE = new JCheckBox("DA - Summoning Circle");
	
	private static final JCheckBox cb_DAILIES = new JCheckBox("DA");
	private static final JCheckBox cb_WEEKLY = new JCheckBox("WEEKLY");
	private static final JCheckBox cb_SUBQUEST = new JCheckBox("SUBQUEST");
	private static final JCheckBox cb_MAIN_QUEST = new JCheckBox("MAIN QUEST");
	private static final JCheckBox cb_CH_STUFF = new JCheckBox("CH STUFF");
	private static final JCheckBox cb_CHALLENGES = new JCheckBox("CHALLENGES");
	private static final JCheckBox cb_SORT_MAIL = new JCheckBox("MAIL");
	private static final JCheckBox cb_INV_SORT = new JCheckBox("INVENTORY");
	private static final JCheckBox cb_DUNGEON_RUN = new JCheckBox("DUNGEON RUN");
	
	private static final JCheckBox cb_DELAYED_RUN = new JCheckBox("START AUTO AT ");
	private static final JTextField tf_DELAYED_RUN = new JTextField("10:05", 5);
	
	private static final JCheckBox cb_SUBQUEST_RESET = new JCheckBox("SUBQUEST RESET [50 Gems]");
	private static final JCheckBox cb_SUBQUEST_BUY_SCROLL_A = new JCheckBox("SUBQUEST Auto Buy Scroll A");
	
	private static final JCheckBox cb_HERBS_EINHASADS = new JCheckBox("HERBS use Einhasad's Blessing");
	
	private static final JTextField setupScreen = new JTextField("", 4);
	private static final JTextField setupScale = new JTextField("", 4);
	
	private static final JButton setupScreenApply = new JButton("Apply");
	private static final JButton setupScaleApply = new JButton("Apply");
	private static final JButton setupScaleTest = new JButton("Test");
	private static final JButton autoConfig = new JButton("Auto Config");

	private static final JTextField setupOffsetX = new JTextField("", 4);
	private static final JTextField setupOffsetY = new JTextField("", 4);
	
	private static final JTextField coordHerbs1X = new JTextField("", 4);
	private static final JTextField coordHerbs1Y = new JTextField("", 4);
	private static final JTextField coordHerbs2X = new JTextField("", 4);
	private static final JTextField coordHerbs2Y = new JTextField("", 4);
	private static final JTextField coordHerbs3X = new JTextField("", 4);
	private static final JTextField coordHerbs3Y = new JTextField("", 4);
	
	private static final JButton btnmap_h1 = new JButton("MAP");
	private static final JButton btnmap_h2 = new JButton("MAP");
	private static final JButton btnmap_h3 = new JButton("MAP");
	

	private static final JTextField D1_WP1X = new JTextField("", 4);
	private static final JTextField D1_WP1Y = new JTextField("", 4);
	private static final JTextField D1_WP1D = new JTextField("", 4);
	private static final JTextField D1_WP2X = new JTextField("", 4);
	private static final JTextField D1_WP2Y = new JTextField("", 4);
	private static final JTextField D1_WP2D = new JTextField("", 4);
	private static final JCheckBox  D1_WP2A = new JCheckBox("Active");
	private static final JTextField D1_WP3X = new JTextField("", 4);
	private static final JTextField D1_WP3Y = new JTextField("", 4);
	private static final JTextField D1_WP3D = new JTextField("", 4);
	private static final JCheckBox  D1_WP3A = new JCheckBox("Active");
	private static final JTextField D1_WP4X = new JTextField("", 4);
	private static final JTextField D1_WP4Y = new JTextField("", 4);
	private static final JTextField D1_WP4D = new JTextField("", 4);
	private static final JCheckBox  D1_WP4A = new JCheckBox("Active");
	private static final JTextField D1_WP5X = new JTextField("", 4);
	private static final JTextField D1_WP5Y = new JTextField("", 4);
	private static final JTextField D1_WP5D = new JTextField("", 4);
	private static final JCheckBox  D1_WP5A = new JCheckBox("Active");
	
	private static final JTextField D2_WP1X = new JTextField("", 4);
	private static final JTextField D2_WP1Y = new JTextField("", 4);
	private static final JTextField D2_WP1D = new JTextField("", 4);
	private static final JTextField D2_WP2X = new JTextField("", 4);
	private static final JTextField D2_WP2Y = new JTextField("", 4);
	private static final JTextField D2_WP2D = new JTextField("", 4);
	private static final JCheckBox  D2_WP2A = new JCheckBox("Active");
	private static final JTextField D2_WP3X = new JTextField("", 4);
	private static final JTextField D2_WP3Y = new JTextField("", 4);
	private static final JTextField D2_WP3D = new JTextField("", 4);
	private static final JCheckBox  D2_WP3A = new JCheckBox("Active");
	private static final JTextField D2_WP4X = new JTextField("", 4);
	private static final JTextField D2_WP4Y = new JTextField("", 4);
	private static final JTextField D2_WP4D = new JTextField("", 4);
	private static final JCheckBox  D2_WP4A = new JCheckBox("Active");
	private static final JTextField D2_WP5X = new JTextField("", 4);
	private static final JTextField D2_WP5Y = new JTextField("", 4);
	private static final JTextField D2_WP5D = new JTextField("", 4);
	private static final JCheckBox  D2_WP5A = new JCheckBox("Active");
	
	private static final JTextField D3_WP1X = new JTextField("", 4);
	private static final JTextField D3_WP1Y = new JTextField("", 4);
	private static final JTextField D3_WP1D = new JTextField("", 4);
	private static final JTextField D3_WP2X = new JTextField("", 4);
	private static final JTextField D3_WP2Y = new JTextField("", 4);
	private static final JTextField D3_WP2D = new JTextField("", 4);
	private static final JCheckBox  D3_WP2A = new JCheckBox("Active");
	private static final JTextField D3_WP3X = new JTextField("", 4);
	private static final JTextField D3_WP3Y = new JTextField("", 4);
	private static final JTextField D3_WP3D = new JTextField("", 4);
	private static final JCheckBox  D3_WP3A = new JCheckBox("Active");
	private static final JTextField D3_WP4X = new JTextField("", 4);
	private static final JTextField D3_WP4Y = new JTextField("", 4);
	private static final JTextField D3_WP4D = new JTextField("", 4);
	private static final JCheckBox  D3_WP4A = new JCheckBox("Active");
	private static final JTextField D3_WP5X = new JTextField("", 4);
	private static final JTextField D3_WP5Y = new JTextField("", 4);
	private static final JTextField D3_WP5D = new JTextField("", 4);
	private static final JCheckBox  D3_WP5A = new JCheckBox("Active");
	
	private static final JTextField D4_WP1X = new JTextField("", 4);
	private static final JTextField D4_WP1Y = new JTextField("", 4);
	private static final JTextField D4_WP1D = new JTextField("", 4);
	private static final JTextField D4_WP2X = new JTextField("", 4);
	private static final JTextField D4_WP2Y = new JTextField("", 4);
	private static final JTextField D4_WP2D = new JTextField("", 4);
	private static final JCheckBox  D4_WP2A = new JCheckBox("Active");
	private static final JTextField D4_WP3X = new JTextField("", 4);
	private static final JTextField D4_WP3Y = new JTextField("", 4);
	private static final JTextField D4_WP3D = new JTextField("", 4);
	private static final JCheckBox  D4_WP3A = new JCheckBox("Active");
	private static final JTextField D4_WP4X = new JTextField("", 4);
	private static final JTextField D4_WP4Y = new JTextField("", 4);
	private static final JTextField D4_WP4D = new JTextField("", 4);
	private static final JCheckBox  D4_WP4A = new JCheckBox("Active");
	private static final JTextField D4_WP5X = new JTextField("", 4);
	private static final JTextField D4_WP5Y = new JTextField("", 4);
	private static final JTextField D4_WP5D = new JTextField("", 4);
	private static final JCheckBox  D4_WP5A = new JCheckBox("Active");
	
	private static final JTextField D5_WP1X = new JTextField("", 4);
	private static final JTextField D5_WP1Y = new JTextField("", 4);
	private static final JTextField D5_WP1D = new JTextField("", 4);
	private static final JTextField D5_WP2X = new JTextField("", 4);
	private static final JTextField D5_WP2Y = new JTextField("", 4);
	private static final JTextField D5_WP2D = new JTextField("", 4);
	private static final JCheckBox  D5_WP2A = new JCheckBox("Active");
	private static final JTextField D5_WP3X = new JTextField("", 4);
	private static final JTextField D5_WP3Y = new JTextField("", 4);
	private static final JTextField D5_WP3D = new JTextField("", 4);
	private static final JCheckBox  D5_WP3A = new JCheckBox("Active");
	private static final JTextField D5_WP4X = new JTextField("", 4);
	private static final JTextField D5_WP4Y = new JTextField("", 4);
	private static final JTextField D5_WP4D = new JTextField("", 4);
	private static final JCheckBox  D5_WP4A = new JCheckBox("Active");
	private static final JTextField D5_WP5X = new JTextField("", 4);
	private static final JTextField D5_WP5Y = new JTextField("", 4);
	private static final JTextField D5_WP5D = new JTextField("", 4);
	private static final JCheckBox  D5_WP5A = new JCheckBox("Active");
	
	private static final JTextField D6_WP1X = new JTextField("", 4);
	private static final JTextField D6_WP1Y = new JTextField("", 4);
	private static final JTextField D6_WP1D = new JTextField("", 4);
	private static final JTextField D6_WP2X = new JTextField("", 4);
	private static final JTextField D6_WP2Y = new JTextField("", 4);
	private static final JTextField D6_WP2D = new JTextField("", 4);
	private static final JCheckBox  D6_WP2A = new JCheckBox("Active");
	private static final JTextField D6_WP3X = new JTextField("", 4);
	private static final JTextField D6_WP3Y = new JTextField("", 4);
	private static final JTextField D6_WP3D = new JTextField("", 4);
	private static final JCheckBox  D6_WP3A = new JCheckBox("Active");
	private static final JTextField D6_WP4X = new JTextField("", 4);
	private static final JTextField D6_WP4Y = new JTextField("", 4);
	private static final JTextField D6_WP4D = new JTextField("", 4);
	private static final JCheckBox  D6_WP4A = new JCheckBox("Active");
	private static final JTextField D6_WP5X = new JTextField("", 4);
	private static final JTextField D6_WP5Y = new JTextField("", 4);
	private static final JTextField D6_WP5D = new JTextField("", 4);
	private static final JCheckBox  D6_WP5A = new JCheckBox("Active");
	

	private static final JButton btnmap_D1WP1 = new JButton("MAP");
	private static final JButton btnmap_D1WP2 = new JButton("MAP");
	private static final JButton btnmap_D1WP3 = new JButton("MAP");
	private static final JButton btnmap_D1WP4 = new JButton("MAP");
	private static final JButton btnmap_D1WP5 = new JButton("MAP");
	
	private static final JButton btnmap_D2WP1 = new JButton("MAP");
	private static final JButton btnmap_D2WP2 = new JButton("MAP");
	private static final JButton btnmap_D2WP3 = new JButton("MAP");
	private static final JButton btnmap_D2WP4 = new JButton("MAP");
	private static final JButton btnmap_D2WP5 = new JButton("MAP");
	
	private static final JButton btnmap_D3WP1 = new JButton("MAP");
	private static final JButton btnmap_D3WP2 = new JButton("MAP");
	private static final JButton btnmap_D3WP3 = new JButton("MAP");
	private static final JButton btnmap_D3WP4 = new JButton("MAP");
	private static final JButton btnmap_D3WP5 = new JButton("MAP");
	
	private static final JButton btnmap_D4WP1 = new JButton("MAP");
	private static final JButton btnmap_D4WP2 = new JButton("MAP");
	private static final JButton btnmap_D4WP3 = new JButton("MAP");
	private static final JButton btnmap_D4WP4 = new JButton("MAP");
	private static final JButton btnmap_D4WP5 = new JButton("MAP");
	
	private static final JButton btnmap_D5WP1 = new JButton("MAP");
	private static final JButton btnmap_D5WP2 = new JButton("MAP");
	private static final JButton btnmap_D5WP3 = new JButton("MAP");
	private static final JButton btnmap_D5WP4 = new JButton("MAP");
	private static final JButton btnmap_D5WP5 = new JButton("MAP");
	
	private static final JButton btnmap_D6WP1 = new JButton("MAP");
	private static final JButton btnmap_D6WP2 = new JButton("MAP");
	private static final JButton btnmap_D6WP3 = new JButton("MAP");
	private static final JButton btnmap_D6WP4 = new JButton("MAP");
	private static final JButton btnmap_D6WP5 = new JButton("MAP");
	
	private static final JButton btn_AcceptSettings = new JButton("ACCEPT");
	
	private static final JTextField setupSleep = new JTextField("", 1);

	private static boolean ez_DA_FREE_DRAW = false;
	private static boolean ez_DA_TOI = false;
	private static boolean ez_DA_FRIENDS = false;
	private static boolean ez_DA_DD = false;
	private static boolean ez_DA_CLAN_DONATION = false;
	private static boolean ez_DA_CLAN_SHOP = false;
	private static boolean ez_DA_CLAN_CHECKIN = false;
	private static boolean ez_DA_CLAN_MEMBERS = false;
	private static boolean ez_DA_ARENA = false;
	private static boolean ez_DA_ELITE_DUNGEON = false;
	private static boolean ez_DA_HERBS = false;
	private static boolean ez_DA_FREE_HG = false;
	private static boolean ez_DA_TEMPLE = false;
	private static boolean ez_DA_VAULT = false;
	private static boolean ez_DA_TRIALS = false;
	private static boolean ez_DA_CIRCLE = false;
	
	private static boolean running = false;
	private static boolean skip = false;
	private static boolean busy = false;
	private static boolean auto = false;
	private static int autosequence = 0; 
	
	private static boolean done_da = false;
	private static boolean done_we = false;
	private static boolean done_sq = false;
	private static boolean done_ms = false;
	private static boolean done_cl = false;
	private static boolean done_ch = false;
	private static boolean done_rm = false;
	private static boolean done_in = false;
	private static boolean done_dr = false;
	
	private static boolean done_dailies = false;
	
	private static int mouseX;
	private static int mouseY;
	
	private static String lastMap;
	
	//configurable stuff
	private static int DEF_SCREEN = 0;
	private static float DEF_SCALE = 100.0f;  
	
	private static int DEF_DA_DUNGEON = 1; // Ivory1
	private static int DEF_DR_DUNGEON = 0; // Cruma3
	
	private static int DEF_GATHER_ON_MAP1_X = 648;
	private static int DEF_GATHER_ON_MAP1_Y = 528;
	private static int DEF_GATHER_ON_MAP2_X = 594;
	private static int DEF_GATHER_ON_MAP2_Y = 634;
	private static int DEF_GATHER_ON_MAP3_X = 816;
	private static int DEF_GATHER_ON_MAP3_Y = 622;

	private static int 		DUNGEON_WP1_X = 0;
	private static int 		DUNGEON_WP1_Y = 0;
	private static int 		DUNGEON_WP1_D = 0;
	private static int 		DUNGEON_WP2_X = 0;
	private static int 		DUNGEON_WP2_Y = 0;
	private static int 		DUNGEON_WP2_D = 0;
	private static int 		DUNGEON_WP3_X = 0;
	private static int 		DUNGEON_WP3_Y = 0;
	private static int 		DUNGEON_WP3_D = 0;
	private static int 		DUNGEON_WP4_X = 0;
	private static int 		DUNGEON_WP4_Y = 0;
	private static int 		DUNGEON_WP4_D = 0;
	private static int 		DUNGEON_WP5_X = 0;
	private static int 		DUNGEON_WP5_Y = 0;
	private static int 		DUNGEON_WP5_D = 0;
	private static boolean 	DUNGEON_WP2_A = false;
	private static boolean 	DUNGEON_WP3_A = false;
	private static boolean 	DUNGEON_WP4_A = false;
	private static boolean 	DUNGEON_WP5_A = false;
	private static int 		DUNGEON_WPLAST_X = 0;
	private static int 		DUNGEON_WPLAST_Y = 0;

	//CRUMA3
	private static int 		DEF_D1_WP1_X = 550;
	private static int 		DEF_D1_WP1_Y = 725;
	private static int 		DEF_D1_WP1_D = 20;
	private static int 		DEF_D1_WP2_X = 775;
	private static int 		DEF_D1_WP2_Y = 500;
	private static int 		DEF_D1_WP2_D = 30;
	private static int 		DEF_D1_WP3_X = 848;
	private static int 		DEF_D1_WP3_Y = 568;
	private static int 		DEF_D1_WP3_D = 10;
	private static int 		DEF_D1_WP4_X = 0;
	private static int 		DEF_D1_WP4_Y = 0;
	private static int 		DEF_D1_WP4_D = 0;
	private static int 		DEF_D1_WP5_X = 0;
	private static int 		DEF_D1_WP5_Y = 0;
	private static int 		DEF_D1_WP5_D = 0;
	private static boolean 	DEF_D1_WP2_A = true;
	private static boolean 	DEF_D1_WP3_A = true;
	private static boolean 	DEF_D1_WP4_A = false;
	private static boolean 	DEF_D1_WP5_A = false;

	//IVORY1
	private static int 		DEF_D2_WP1_X = 829;
	private static int 		DEF_D2_WP1_Y = 741;
	private static int 		DEF_D2_WP1_D = 8;
	private static int 		DEF_D2_WP2_X = 654;
	private static int 		DEF_D2_WP2_Y = 735;
	private static int 		DEF_D2_WP2_D = 10;
	private static int 		DEF_D2_WP3_X = 467;
	private static int 		DEF_D2_WP3_Y = 646;
	private static int 		DEF_D2_WP3_D = 15;
	private static int 		DEF_D2_WP4_X = 0;
	private static int 		DEF_D2_WP4_Y = 0;
	private static int 		DEF_D2_WP4_D = 0;
	private static int 		DEF_D2_WP5_X = 0;
	private static int 		DEF_D2_WP5_Y = 0;
	private static int 		DEF_D2_WP5_D = 0;
	private static boolean 	DEF_D2_WP2_A = true;
	private static boolean 	DEF_D2_WP3_A = true;
	private static boolean 	DEF_D2_WP4_A = false;
	private static boolean 	DEF_D2_WP5_A = false;
	
	//IVORY2
	private static int 		DEF_D3_WP1_X = 705;
	private static int 		DEF_D3_WP1_Y = 676;
	private static int 		DEF_D3_WP1_D = 15;
	private static int 		DEF_D3_WP2_X = 783;
	private static int 		DEF_D3_WP2_Y = 778;
	private static int 		DEF_D3_WP2_D = 15;
	private static int 		DEF_D3_WP3_X = 0;
	private static int 		DEF_D3_WP3_Y = 0;
	private static int 		DEF_D3_WP3_D = 0;
	private static int 		DEF_D3_WP4_X = 0;
	private static int 		DEF_D3_WP4_Y = 0;
	private static int 		DEF_D3_WP4_D = 0;
	private static int 		DEF_D3_WP5_X = 0;
	private static int 		DEF_D3_WP5_Y = 0;
	private static int 		DEF_D3_WP5_D = 0;
	private static boolean 	DEF_D3_WP2_A = true;
	private static boolean 	DEF_D3_WP3_A = false;
	private static boolean 	DEF_D3_WP4_A = false;
	private static boolean 	DEF_D3_WP5_A = false;
	
	//IVORY3
	private static int 		DEF_D4_WP1_X = 731;
	private static int 		DEF_D4_WP1_Y = 546;
	private static int 		DEF_D4_WP1_D = 15;
	private static int 		DEF_D4_WP2_X = 572;
	private static int 		DEF_D4_WP2_Y = 711;
	private static int 		DEF_D4_WP2_D = 20;
	private static int 		DEF_D4_WP3_X = 493;
	private static int 		DEF_D4_WP3_Y = 646;
	private static int 		DEF_D4_WP3_D = 15;
	private static int 		DEF_D4_WP4_X = 0;
	private static int 		DEF_D4_WP4_Y = 0;
	private static int 		DEF_D4_WP4_D = 0;
	private static int 		DEF_D4_WP5_X = 0;
	private static int 		DEF_D4_WP5_Y = 0;
	private static int 		DEF_D4_WP5_D = 0;
	private static boolean 	DEF_D4_WP2_A = true;
	private static boolean 	DEF_D4_WP3_A = true;
	private static boolean 	DEF_D4_WP4_A = false;
	private static boolean 	DEF_D4_WP5_A = false;
	
	//FOS CANOPY
	private static int 		DEF_D5_WP1_X = 550;
	private static int 		DEF_D5_WP1_Y = 725;
	private static int 		DEF_D5_WP1_D = 20;
	private static int 		DEF_D5_WP2_X = 0;
	private static int 		DEF_D5_WP2_Y = 0;
	private static int 		DEF_D5_WP2_D = 0;
	private static int 		DEF_D5_WP3_X = 0;
	private static int 		DEF_D5_WP3_Y = 0;
	private static int 		DEF_D5_WP3_D = 0;
	private static int 		DEF_D5_WP4_X = 0;
	private static int 		DEF_D5_WP4_Y = 0;
	private static int 		DEF_D5_WP4_D = 0;
	private static int 		DEF_D5_WP5_X = 0;
	private static int 		DEF_D5_WP5_Y = 0;
	private static int 		DEF_D5_WP5_D = 0;
	private static boolean 	DEF_D5_WP2_A = false;
	private static boolean 	DEF_D5_WP3_A = false;
	private static boolean 	DEF_D5_WP4_A = false;
	private static boolean 	DEF_D5_WP5_A = false;
	
	//FOS UNDERSTORY
	private static int 		DEF_D6_WP1_X = 550;
	private static int 		DEF_D6_WP1_Y = 725;
	private static int 		DEF_D6_WP1_D = 20;
	private static int 		DEF_D6_WP2_X = 0;
	private static int 		DEF_D6_WP2_Y = 0;
	private static int 		DEF_D6_WP2_D = 0;
	private static int 		DEF_D6_WP3_X = 0;
	private static int 		DEF_D6_WP3_Y = 0;
	private static int 		DEF_D6_WP3_D = 0;
	private static int 		DEF_D6_WP4_X = 0;
	private static int 		DEF_D6_WP4_Y = 0;
	private static int 		DEF_D6_WP4_D = 0;
	private static int 		DEF_D6_WP5_X = 0;
	private static int 		DEF_D6_WP5_Y = 0;
	private static int 		DEF_D6_WP5_D = 0;
	private static boolean 	DEF_D6_WP2_A = false;
	private static boolean 	DEF_D6_WP3_A = false;
	private static boolean 	DEF_D6_WP4_A = false;
	private static boolean 	DEF_D6_WP5_A = false;
	
	private static double 	DEF_SLEEP = 1;
	
	private static String[] dungeonStrings = { "Cruma Tower Floor 3", "Ivory Tower Catacomb 1", "Ivory Tower Catacomb 2", "Ivory Tower Catacomb 3", "Forest of Secrets Canopy", "Forest of Secrets Understory" };

	private static JComboBox<String> dungeonListDA = new JComboBox<>(dungeonStrings);
	private static JComboBox<String> dungeonListDR = new JComboBox<>(dungeonStrings);
	
	private static boolean inclan = true;
	private static boolean dungeonInit = true;
	private static boolean dungeonRunMode = false;
	
	private static JFrame frame1;
	private static JFrame frame2;
	private static JDialog frame3;
	private static JDialog frame4;
	
	private static JLabel ac_screen = new JLabel("0");
	private static JLabel ac_scale = new JLabel("--");
	private static JLabel ac_offsetx = new JLabel("0");
	private static JLabel ac_offsety = new JLabel("0");
	
	private static JDialog frameMap;

	private static final Settings setari = new Settings();
	
	private static float ACCURACY = 0.8f; //a fost 0.9 dar cu 0.7 merge si cu bluestacks  
	private static float ACC_LOW = 0.5f;
	private static float ACC_DRAW = 0.89f;
	private static float ACC_HIGH = 0.9f;
	private static float ACC_095 = 0.95f;
	private static float ACC_096 = 0.96f;
	private static float ACC_097 = 0.97f;
	private static float ACC_099 = 0.99f;
	private static float ACC_PVP = 0.95f; 
	private static float ACC_MAX = 0.98f;
	
	private static int DEFDELAY = 5; //timpul de cautare dupa buton (1 = 2 sec ...)
	
	private static int BTN_DA_OFFSET = 1600; //indent from search area to Perform button
	private static int BTN_ARENA_OFFSET = 310; //indent from search area to Start PVP button
	private static int BTN_QUEST_X = 110; 
	private static int BTN_QUEST_Y = 320; 
	private static int BTN_PARTY_X = 310; 
	private static int BTN_PARTY_Y = 320; 
	private static int BTN_MNU_X = 1370; 
	private static int BTN_MNU_Y = 100; 
	private static int BTN_INV_X = 1470; 
	private static int BTN_INV_Y = 100; 
	private static int BTN_SHOP_X = 1560;
	private static int BTN_SHOP_Y = 100;
	private static int BTN_MAIL_X = 1560;
	private static int BTN_MAIL_Y = 176;
	private static int BTN_MAP_X = 1760;
	private static int BTN_MAP_Y = 212;
	private static int BTN_LEAVE_DUNGEON_X = 1290;
	private static int BTN_LEAVE_DUNGEON_Y = 176;
	private static int BTN_POTIONS_X = 1500;
	private static int BTN_POTIONS_Y = 600;
	private static int GATHERPOTIONX = 1500;
	private static int GATHERPOTIONY = 120;
	private static int GATHERBTNX = 1036;
	private static int GATHERBTNY = 438;
	private static int BTN_AUTO_X = 1400;
	private static int BTN_AUTO_Y = 950;
	private static int BTN_CH_MERCHANT_X = 1470; 
	private static int BTN_CH_MERCHANT_Y = 260; 
	private static int BTN_CH_MENU_X = 1570; 
	private static int BTN_CH_MENU_Y = 260; 
	private static int BTN_CH_RELIC_X = 1550; 
	private static int BTN_CH_RELIC_Y = 600;
	
	private static final int OFFSETX_INIT = 12;
	private static final int OFFSETY_INIT = 46;
	private static int DEF_OFFSETX = 12; //12
	private static int DEF_OFFSETY = 46;

	private static Screen bbs;  // (0) sau () pt primul monitor, (1) pt al doilea etc
	private static int DEFX; //screen(1) = 1920
	
	private static int dailyTimer = 0;
	
	private static int DEF_STAGE_PAUSE = 15000;
	
	private static String[] emulatorStrings = { "Bluestacks", "MEMU", "NOX" };
	private static JComboBox<String> emulatorList = new JComboBox<>(emulatorStrings);

	private static String[] resolutionStrings = { "1920x1200", "1920x1080"};
	private static JComboBox<String> resolutionList = new JComboBox<>(resolutionStrings);
	
	//Bluestacks -> 1200p Scale:100% OffsetX:12/Y:46, 1080p:88.5% 119/46 | Memu: 1200p:99.4% 2/84, 1080p:98.2% 12/31
	private static final String	DEF_BLUESTACKS_1200_SCALE = "100";
	private static final String	DEF_BLUESTACKS_1200_OFF_X = "12";
	private static final String	DEF_BLUESTACKS_1200_OFF_Y = "46";
	private static final String	DEF_BLUESTACKS_1080_SCALE = "88.5";
	private static final String	DEF_BLUESTACKS_1080_OFF_X = "119";
	private static final String	DEF_BLUESTACKS_1080_OFF_Y = "46";
	private static final String	DEF_MEMU_1200_SCALE = "99.4";
	private static final String	DEF_MEMU_1200_OFF_X = "2";
	private static final String	DEF_MEMU_1200_OFF_Y = "84";
	private static final String	DEF_MEMU_1080_SCALE = "98.2";
	private static final String	DEF_MEMU_1080_OFF_X = "12";
	private static final String	DEF_MEMU_1080_OFF_Y = "31";
	private static final String	DEF_NOX_1200_SCALE = "99.2";
	private static final String	DEF_NOX_1200_OFF_X = "2";
	private static final String	DEF_NOX_1200_OFF_Y = "30";
	private static final String	DEF_NOX_1080_SCALE = "94.6";
	private static final String	DEF_NOX_1080_OFF_X = "46";
	private static final String	DEF_NOX_1080_OFF_Y = "30";
	
	public static void main(String[] args) {

		frame1 = new JFrame("L2RBot 5000 v1.1.21 build 420");
		
		frame1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame1.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				quit();
			}
		});
		
		JPanel panel = new JPanel();
		frame1.add(panel);

		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		EmptyBorder b1 = new EmptyBorder(10,10,2,10);
		EmptyBorder b2 = new EmptyBorder(4,10,4,10);
		
		gc.anchor = GridBagConstraints.WEST;
		gc.fill = GridBagConstraints.BOTH;
		
		gc.gridx = 0;
		gc.gridy = 0;
		btn_main_quest.setToolTipText("Does Main Story if available.");
		panel.add(btn_main_quest, gc);
		btn_main_quest.addActionListener(new Action1());

		gc.gridx = 1;
		gc.gridy = 0;
		btn_weekly.setToolTipText("Does the 10 Weekly missions.");
		panel.add(btn_weekly, gc);
		btn_weekly.addActionListener(new Action2());

		gc.gridx = 2;
		gc.gridy = 0;
		btn_subquests.setToolTipText("Does the 5 Subquest missions. Will use 50 gems to reset if setup. Will buy Scroll A if setup.");
		panel.add(btn_subquests, gc);
		btn_subquests.addActionListener(new Action3());

		gc.gridx = 3;
		gc.gridy = 0;
		btn_da.setToolTipText("Does the Daily Activities in order.");
		panel.add(btn_da, gc);
		btn_da.addActionListener(new Action4());

		gc.gridx = 4;
		gc.gridy = 0;
		btn_ch_stuff.setToolTipText("Collects Clan Hall Merchant Gift and appraises Relics.");
		panel.add(btn_ch_stuff, gc);
		btn_ch_stuff.addActionListener(new Action6());

		gc.gridx = 5;
		gc.gridy = 0;
		btn_challenges.setToolTipText("Claims Achievements and Lists Codexes.");
		panel.add(btn_challenges, gc);
		btn_challenges.addActionListener(new Action7());

		gc.gridx = 6;
		gc.gridy = 0;
		btn_mail.setToolTipText("Reads all Mail.");
		panel.add(btn_mail, gc);
		btn_mail.addActionListener(new Action8());

		gc.gridx = 7;
		gc.gridy = 0;
		btn_inv.setToolTipText("Opens all Boxes in Inventory.Makes choices based on config (see images/configurable folder)");
		panel.add(btn_inv, gc);
		btn_inv.addActionListener(new Action9());

		gc.gridx = 8;
		gc.gridy = 0;
		btn_dr.setToolTipText("Auto enters Elite Dungeon selected in settings. (auto joins first available party)");
		panel.add(btn_dr, gc);
		btn_dr.addActionListener(new Action10());

		gc.gridx = 9;
		gc.gridy = 0;
		btn_dr_noinit.setToolTipText("Continue Elite Dungeon run. Use if already there.");
		panel.add(btn_dr_noinit, gc);
		btn_dr_noinit.addActionListener(new Action10b());

		gc.gridx = 10;
		gc.gridy = 0;
		panel.add(btn_settings, gc);
		btn_settings.addActionListener(new Action_Settings());

		gc.gridx = 0;
		gc.gridy = 1;
		JLabel t1 = new JLabel("LOG");
		t1.setBorder(b1);
		panel.add(t1, gc);

		gc.gridx = 4;
		gc.gridy = 1;
		gc.gridwidth = 3;
		JLabel t2 = new JLabel("DA SETTINGS");
		t2.setBorder(b1);
		panel.add(t2, gc);

		gc.gridx = 7;
		gc.gridy = 1;
		gc.gridwidth = 2;
		JLabel t3 = new JLabel("AUTO SETTINGS");
		t3.setBorder(b1);
		panel.add(t3, gc);

		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridwidth = 4;
		gc.gridheight = 18;
		
		//auto scroll
	    DefaultCaret caret = (DefaultCaret) talog.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
	    JScrollPane scroll = new JScrollPane(talog);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    panel.add(scroll, gc);

		gc.gridwidth = 3;
		gc.gridheight = 1;

	    gc.gridx = 4;
		gc.gridy = 4;
		cb_DA_FREE_DRAW.setBorder(b2);
		cb_DA_FREE_DRAW.setToolTipText("Collects Free Equipment Box using Friendship points from Shop.");
	    panel.add(cb_DA_FREE_DRAW, gc);
	    
	    gc.gridx = 4;
		gc.gridy = 5;
		cb_DA_TOI.setBorder(b2);
		cb_DA_TOI.setToolTipText("Auto Clears Tower of Insolence. Does NOT enter.");
	    panel.add(cb_DA_TOI, gc);
	    
	    gc.gridx = 4;
		gc.gridy = 6;
		cb_DA_FRIENDS.setBorder(b2);
		cb_DA_FRIENDS.setToolTipText("Greets/Collects Friends.");
	    panel.add(cb_DA_FRIENDS, gc);
	    
	    gc.gridx = 4;
		gc.gridy = 7;
		cb_DA_DD.setBorder(b2);
		cb_DA_DD.setToolTipText("Enters Daily Dungeon. (No Autocomplete is used)");
	    panel.add(cb_DA_DD, gc);
	    
	    gc.gridx = 4;
		gc.gridy = 8;
		cb_DA_CLAN_CHECKIN.setBorder(b2);
		cb_DA_CLAN_CHECKIN.setToolTipText("Checks into Clan.");
	    panel.add(cb_DA_CLAN_CHECKIN, gc);
	    
	    gc.gridx = 4;
		gc.gridy = 9;
		cb_DA_CLAN_MEMBERS.setBorder(b2);
		cb_DA_CLAN_MEMBERS.setToolTipText("Greets/Collects Clan Members.");
	    panel.add(cb_DA_CLAN_MEMBERS, gc);
	    
	    gc.gridx = 4;
		gc.gridy = 10;
		cb_DA_CLAN_DONATION.setBorder(b2);
		cb_DA_CLAN_DONATION.setToolTipText("Donates 75k Adena to Clan.");
	    panel.add(cb_DA_CLAN_DONATION, gc);
	    
	    gc.gridx = 4;
		gc.gridy = 11;
		cb_DA_CLAN_SHOP.setBorder(b2);
		cb_DA_CLAN_SHOP.setToolTipText("Buys Greater Exp. from Clan Shop if available.");
	    panel.add(cb_DA_CLAN_SHOP, gc);
	    
	    gc.gridx = 4;
		gc.gridy = 12;
		cb_DA_ARENA.setBorder(b2);
		cb_DA_ARENA.setToolTipText("Collects arena reward and does 5 x PvP (TO CONFIGURE SEE IMAGES FOLDER !!!).");
	    panel.add(cb_DA_ARENA, gc);
	    
	    gc.gridx = 4;
		gc.gridy = 13;
		cb_DA_ELITE_DUNGEON.setBorder(b2);
		cb_DA_ELITE_DUNGEON.setToolTipText("Autocompletes Elite Dungeon using 50 Gems.");
	    panel.add(cb_DA_ELITE_DUNGEON, gc);
	    
	    gc.gridx = 4;
		gc.gridy = 14;
		cb_DA_HERBS.setBorder(b2);
		cb_DA_HERBS.setToolTipText("Gathers Herbs. Position configurable. If killed goes to 2nd/3rd pos. Will use Enhasads Blessing if configured.");
	    panel.add(cb_DA_HERBS, gc);
	    
	    gc.gridx = 4;
		gc.gridy = 15;
		cb_DA_FREE_HG.setBorder(b2);
		cb_DA_FREE_HG.setToolTipText("Collects High-grade Equipment Box from Shop if available.");
	    panel.add(cb_DA_FREE_HG, gc);
	    
	    gc.gridx = 4;
		gc.gridy = 16;
		cb_DA_TEMPLE.setBorder(b2);
		cb_DA_TEMPLE.setToolTipText("Performs 2x Temple Guardian.");
	    panel.add(cb_DA_TEMPLE, gc);
	    
	    gc.gridx = 4;
		gc.gridy = 17;
		cb_DA_VAULT.setBorder(b2);
		cb_DA_VAULT.setToolTipText("Tries to autoclear Adena Vault. If not enters.");
	    panel.add(cb_DA_VAULT, gc);
	    
	    gc.gridx = 4;
		gc.gridy = 18;
		cb_DA_TRIALS.setBorder(b2);
		cb_DA_TRIALS.setToolTipText("Tries to autoclear Trials of Experience. If not enters.");
	    panel.add(cb_DA_TRIALS, gc);
	    
	    gc.gridx = 4;
		gc.gridy = 19;
		cb_DA_CIRCLE.setBorder(b2);
		cb_DA_CIRCLE.setToolTipText("Performs 2x Summoning Circle.");
	    panel.add(cb_DA_CIRCLE, gc);
	    
		gc.gridwidth = 2;
		gc.gridheight = 1;

	    gc.gridx = 7;
		gc.gridy = 4;
		cb_DAILIES.setBorder(b2);
		cb_DAILIES.setToolTipText("[AUTO] Does the Daily Activities in order.");
	    panel.add(cb_DAILIES, gc);
	    
	    gc.gridx = 7;
		gc.gridy = 5;
		cb_WEEKLY.setBorder(b2);
		cb_WEEKLY.setToolTipText("[AUTO] Does the 10 Weekly missions.");
	    panel.add(cb_WEEKLY, gc);
	    
	    gc.gridx = 7;
		gc.gridy = 6;
		cb_SUBQUEST.setBorder(b2);
		cb_SUBQUEST.setToolTipText("[AUTO] Does the 5 Subquest missions. Will use 50 gems to reset if setup. Will buy Scroll A if setup.");
	    panel.add(cb_SUBQUEST, gc);
	    
	    gc.gridx = 7;
		gc.gridy = 7;
		cb_MAIN_QUEST.setBorder(b2);
		cb_MAIN_QUEST.setToolTipText("[AUTO] Does Main Story if available.");
	    panel.add(cb_MAIN_QUEST, gc);
	    
	    gc.gridx = 7;
		gc.gridy = 8;
		cb_CH_STUFF.setBorder(b2);
		cb_CH_STUFF.setToolTipText("[AUTO] Collects Clan Hall Merchant Gift and appraises Relics.");
	    panel.add(cb_CH_STUFF, gc);
	    
	    gc.gridx = 7;
		gc.gridy = 9;
		cb_CHALLENGES.setBorder(b2);
		cb_CHALLENGES.setToolTipText("[AUTO] Claims Achievements and Lists Codexes.");
	    panel.add(cb_CHALLENGES, gc);
	    
	    gc.gridx = 7;
		gc.gridy = 10;
		cb_SORT_MAIL.setBorder(b2);
		cb_SORT_MAIL.setToolTipText("[AUTO] Reads all Mail.");
	    panel.add(cb_SORT_MAIL, gc);
	    
	    gc.gridx = 7;
		gc.gridy = 11;
		cb_INV_SORT.setBorder(b2);
		cb_INV_SORT.setToolTipText("[AUTO] Opens all Boxes in Inventory. Automatically makes choices. (TO CONFIGURE SEE IMAGES FOLDER !!!)");
	    panel.add(cb_INV_SORT, gc);
	    
	    gc.gridx = 7;
		gc.gridy = 12;
		cb_DUNGEON_RUN.setBorder(b2);
		cb_DUNGEON_RUN.setToolTipText("[AUTO] Enters Elite Dungeon selected in settings. (auto joins first available party)");
	    panel.add(cb_DUNGEON_RUN, gc);
	    
	    gc.gridx = 7;
		gc.gridy = 14;
		cb_DELAYED_RUN.setBorder(b2);
		cb_DELAYED_RUN.setToolTipText("This option will start AUTO mode at set time IF in Dungeon Run!");
	    panel.add(cb_DELAYED_RUN, gc);
	    
		gc.gridx = 9;
		gc.gridy = 14;
		gc.gridwidth = 1;
		panel.add(tf_DELAYED_RUN, gc);
	    
		frame1.pack();
		frame1.setSize(1000, 500);
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
		frame1.setResizable(false);
		
/*		
		GraphicsConfiguration config = frame.getGraphicsConfiguration();
		GraphicsDevice myScreen = config.getDevice();
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] allScreens = env.getScreenDevices();
		int myScreenIndex = -1;
		for (int i = 0; i < allScreens.length; i++) {
		    if (allScreens[i].equals(myScreen))
		    {
		        myScreenIndex = i;
		        break;
		    }
		}
		
	    javax.swing.JOptionPane.showConfirmDialog((java.awt.Component)
		        null, "Screen : " + myScreenIndex, "screen detected ?",
		        javax.swing.JOptionPane.DEFAULT_OPTION);
*/		
		
		frame2 = new JFrame("Info Window");
        frame2.setType(Type.UTILITY);
        frame2.setUndecorated(true);
        //frame2.setBackground(new Color(0, 0, 0, 0));
        frame2.setAlwaysOnTop(true);
        frame2.setVisible(true);
        frame2.setSize(500, 20);

        JPanel panel2 = new JPanel();
        //frame2.getContentPane().add(panel2);
		frame2.add(panel2);
		panel2.setPreferredSize(new Dimension(500,20));
		panel2.setLayout(new GridBagLayout());
		
		GridBagConstraints gc2 = new GridBagConstraints();
		
		gc2.anchor = GridBagConstraints.WEST;
		gc2.fill = GridBagConstraints.BOTH;
		
		gc2.gridx = 0;
		gc2.gridy = 0;
		gc2.weightx = 0f;
		JLabel labelSpc = new JLabel("  ");
        panel2.add(labelSpc, gc2);
        
		gc2.gridx = 1;
		gc2.gridy = 0;
		gc2.weightx = 1f;
        infoBox.setFont(new Font("Arial", Font.PLAIN, 10));
        infoBox.setPreferredSize(new Dimension(300,20));
        panel2.add(infoBox, gc2);
        
		gc2.gridx = 2;
		gc2.gridy = 0;
		gc2.weightx = 0f;
		panel2.add(btn_AUTO, gc2);
		btn_AUTO.addActionListener(new Action5());
        
		gc2.gridx = 3;
		gc2.gridy = 0;
		gc2.weightx = 0f;
		btn_stop.setEnabled(false);
		panel2.add(btn_stop, gc2);
		btn_stop.addActionListener(new ActionStop());

		gc2.gridx = 4;
		gc2.gridy = 0;
		gc2.weightx = 0f;
		btn_skip.setEnabled(false);
		panel2.add(btn_skip, gc2);
		btn_skip.addActionListener(new ActionSkip());
		
		//gc2.fill = GridBagConstraints.NONE;

		gc2.gridx = 5;
		gc2.gridy = 0;
		gc2.weightx = 0f;
		panel2.add(btn_exit, gc2);
		btn_exit.addActionListener(new ActionExit());

		frame2.pack();

		frame3 = new JDialog(frame1, "Settings", true);
		JPanel panel3 = new JPanel();
		frame3.getContentPane().add(panel3);
		frame3.pack();
		frame3.setVisible(false);
        frame3.setSize(1300, 700);
		frame3.setLocationRelativeTo( null );
		frame3.setResizable(false);
		
		panel3.setPreferredSize(new Dimension(1300,700));
		panel3.setLayout(new GridBagLayout());
		
		GridBagConstraints gc3 = new GridBagConstraints();
		
		gc3.anchor = GridBagConstraints.WEST;
		gc3.fill = GridBagConstraints.BOTH;

		int startRow = 0;

		
		gc3.gridwidth = 8;
		gc3.gridx = 1;
		gc3.gridy = startRow;
		JLabel labelInfo = new JLabel("Emulator resolution should be 1280x720 / 160Dpi and Game Graphics should be all Low ! ");
		labelInfo.setBorder(b2);
		panel3.add(labelInfo, gc3);		
		
		startRow++;
/*		
		gc3.gridwidth = 1;
		gc3.gridx = 2;
		gc3.gridy = startRow;
		JLabel labelPre = new JLabel("CONFIG");
		labelPre.setBorder(b2);
		panel3.add(labelPre, gc3);		
*/
		
		gc3.gridwidth = 5;
		gc3.gridx = 2;
		gc3.gridy = startRow;
		panel3.add(autoConfig, gc3);
		autoConfig.addActionListener(new Action_AutoConfig());
		
		
/*		
		gc3.gridwidth = 1;
		gc3.gridx = 1;
		gc3.gridy = startRow;
		JLabel labelEmu = new JLabel("Emulator: ");
		labelEmu.setBorder(b2);
		panel3.add(labelEmu, gc3);	
		
		gc3.gridwidth = 2;
		gc3.gridx = 2;
		gc3.gridy = startRow;
		panel3.add(emulatorList, gc3);
		
		gc3.gridwidth = 1;
		gc3.gridx = 4;
		gc3.gridy = startRow;
		JLabel labelRes = new JLabel("Resolution: ");
		labelRes.setBorder(b2);
		panel3.add(labelRes, gc3);		
		
		gc3.gridwidth = 2;
		gc3.gridx = 5;
		gc3.gridy = startRow;
		panel3.add(resolutionList, gc3);
*/		
		startRow++;
		
		int currentCol = 8;
		int currentRow = startRow;

		gc3.gridwidth = 1;
		gc3.gridx = 8;
		gc3.gridy = currentRow;
		JLabel labelSP1 = new JLabel("              ");
		labelSP1.setBorder(b2);
		panel3.add(labelSP1, gc3);
		
		gc3.gridwidth = 1;
		gc3.gridx = 16;
		gc3.gridy = currentRow;
		JLabel labelSP2 = new JLabel("              ");
		labelSP2.setBorder(b2);
		panel3.add(labelSP2, gc3);
		
		int llspan = 23;
		String long_line = "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";

		startRow++;

		currentCol = 0;
		currentRow = startRow;
		
	    gc3.gridwidth = 1;
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel scrl = new JLabel("SCREEN #:");
		scrl.setBorder(b2);
		panel3.add(scrl, gc3);
		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(setupScreen, gc3);
		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(setupScreenApply, gc3);
		setupScreenApply.addActionListener(new Action_ApplyScreen());
		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		gc3.gridwidth = 3;
		JLabel scrinfo = new JLabel("(0-First Monitor, 1-Second)");
		scrinfo.setBorder(b2);
		panel3.add(scrinfo, gc3);

		currentRow++;
		
	    gc3.gridwidth = 1;
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel scal = new JLabel("SCALE %:");
		scal.setToolTipText("Emulator width in percent 100% being 1900 (windowed fullscreen)");
		scal.setBorder(b2);
		panel3.add(scal, gc3);
		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(setupScale, gc3);
		setupScale.setToolTipText("Emulator width in percent 100% being 1900 (windowed fullscreen)");
		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(setupScaleApply, gc3);
		setupScaleApply.addActionListener(new Action_ApplyScale());
		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(setupScaleTest, gc3);
		setupScaleTest.addActionListener(new Action_TestScale());

		
/*
		JLabel scainfo = new JLabel("(Emulator width in percent)");
		scainfo.setBorder(b2);
		panel3.add(scainfo, gc3);
*/
		currentRow++;
		
	    gc3.gridwidth = 1;
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel offl = new JLabel("OFFSET X AND Y:");
		offl.setBorder(b2);
		panel3.add(offl, gc3);
		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(setupOffsetX, gc3);
		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(setupOffsetY, gc3);
		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		gc3.gridwidth = 6;
		JLabel offinfo = new JLabel("(Dist. from top-left corner to emulator)");
		offinfo.setBorder(b2);
		panel3.add(offinfo, gc3);
		
		currentCol = 8;
		currentRow = startRow-3;
		
		gc3.gridwidth = 4;
		
	    gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		cb_SUBQUEST_RESET.setBorder(b2);
	    panel3.add(cb_SUBQUEST_RESET, gc3);
	    
	    currentRow++;

	    gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		cb_SUBQUEST_BUY_SCROLL_A.setBorder(b2);
	    panel3.add(cb_SUBQUEST_BUY_SCROLL_A, gc3);

		currentRow++;
		
		gc3.gridwidth = 3;
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel labelDD = new JLabel("DA - Elite Dungeon Auto");
		labelDD.setBorder(b2);
		panel3.add(labelDD, gc3);
		
		gc3.gridwidth = 3;
		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(dungeonListDA, gc3);

	    currentRow++;
	    
		gc3.gridwidth = 4;
	    gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		cb_HERBS_EINHASADS.setBorder(b2);
	    panel3.add(cb_HERBS_EINHASADS, gc3);

	    currentRow++;
	    
	    gc3.gridwidth = 3;
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel thm1 = new JLabel("HERBS AREA 1");
		thm1.setBorder(b2);
		panel3.add(thm1, gc3);
		
		gc3.gridwidth = 1;
		
		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(coordHerbs1X, gc3);

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(coordHerbs1Y, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(btnmap_h1, gc3);
		btnmap_h1.addActionListener(new ActionMapH1());

		currentRow++;
		
		gc3.gridwidth = 3;
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel thm2 = new JLabel("HERBS AREA 2");
		thm2.setBorder(b2);
		panel3.add(thm2, gc3);

		gc3.gridwidth = 1;
		
		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(coordHerbs2X, gc3);

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(coordHerbs2Y, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(btnmap_h2, gc3);
		btnmap_h2.addActionListener(new ActionMapH2());

		currentRow++;
		
		gc3.gridwidth = 3;
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel thm3 = new JLabel("HERBS AREA 3");
		thm3.setBorder(b2);
		panel3.add(thm3, gc3);
		
		gc3.gridwidth = 1;
		
		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(coordHerbs3X, gc3);

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(coordHerbs3Y, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(btnmap_h3, gc3);
		btnmap_h3.addActionListener(new ActionMapH3());
		
		gc3.gridwidth = 4;
		gc3.gridx = currentCol+9;
		gc3.gridy = currentRow-6;
		JLabel label_sleep = new JLabel("PAUSE MODIFIER (SHOULD BE 1.0):");
		label_sleep.setToolTipText("This represents delay multiplier (0.5 = 2xfaster, 2 = 2xslower)");
		label_sleep.setBorder(b2);
		panel3.add(label_sleep, gc3);
		gc3.gridwidth = 1;
		gc3.gridx = currentCol+13;
		gc3.gridy = currentRow-6;
		setupSleep.setToolTipText("This represents delay multiplier (0.5 = 2xfaster, 2 = 2xslower)");
		panel3.add(setupSleep, gc3);
		
		currentCol = 0;

		startRow = currentRow+1;
		
		gc3.gridwidth = llspan;
		gc3.gridx = 0;
		gc3.gridy = startRow;
		JLabel labelLine = new JLabel(long_line);
		panel3.add(labelLine, gc3);
		
		startRow++;
		
		gc3.gridwidth = 3;
		gc3.gridx = currentCol+1;
		gc3.gridy = startRow;
		JLabel labelDR = new JLabel("DUNGEON RUN");
		labelDR.setBorder(b2);
		panel3.add(labelDR, gc3);
		
		gc3.gridwidth = 3;
		gc3.gridx = currentCol+4;
		gc3.gridy = startRow;
		panel3.add(dungeonListDR, gc3);

		startRow++;
		
		int titleSize = 16;
		
		currentCol = 0;
		currentRow = startRow;
		
		gc3.gridwidth = 6;
		
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel dungeonL1 = new JLabel("Cruma Tower Floor 3");
		dungeonL1.setFont(new Font("Arial", Font.PLAIN, titleSize));
		dungeonL1.setBorder(b2);
		panel3.add(dungeonL1, gc3);
		
		currentRow++;
		
	    gc3.gridwidth = 1;
	    
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D1WP1 = new JLabel("WP 1");
		D1WP1.setBorder(b2);
		panel3.add(D1WP1, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D1_WP1X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D1_WP1Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D1WP1, gc3);
		btnmap_D1WP1.addActionListener(new ActionMapD1WP1());

		EmptyBorder bdelay = new EmptyBorder(10,10,0,10);
		
		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow-1;
		JLabel l_delay = new JLabel("Delay (s)");
		l_delay.setBorder(bdelay);
		panel3.add(l_delay, gc3);
		
		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D1_WP1D, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D1WP2 = new JLabel("WP 2");
		D1WP2.setBorder(b2);
		panel3.add(D1WP2, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D1_WP2X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D1_WP2Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D1WP2, gc3);
		btnmap_D1WP2.addActionListener(new ActionMapD1WP2());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D1_WP2D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D1_WP2A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D1WP3 = new JLabel("WP 3");
		D1WP3.setBorder(b2);
		panel3.add(D1WP3, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D1_WP3X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D1_WP3Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D1WP3, gc3);
		btnmap_D1WP3.addActionListener(new ActionMapD1WP3());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D1_WP3D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D1_WP3A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D1WP4 = new JLabel("WP 4");
		D1WP4.setBorder(b2);
		panel3.add(D1WP4, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D1_WP4X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D1_WP4Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D1WP4, gc3);
		btnmap_D1WP4.addActionListener(new ActionMapD1WP4());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D1_WP4D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D1_WP4A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D1WP5 = new JLabel("WP 5");
		D1WP5.setBorder(b2);
		panel3.add(D1WP5, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D1_WP5X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D1_WP5Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D1WP5, gc3);
		btnmap_D1WP5.addActionListener(new ActionMapD1WP5());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D1_WP5D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D1_WP5A, gc3);



		
		currentCol += 8;
		currentRow = startRow;
		
		gc3.gridwidth = 6;
		
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel dungeonL2 = new JLabel("Ivory Tower Catacomb 1");
		dungeonL2.setFont(new Font("Arial", Font.PLAIN, titleSize));
		dungeonL2.setBorder(b2);
		panel3.add(dungeonL2, gc3);
		
		currentRow++;
		
	    gc3.gridwidth = 1;
	    
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D2WP1 = new JLabel("WP 1");
		D2WP1.setBorder(b2);
		panel3.add(D2WP1, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D2_WP1X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D2_WP1Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D2WP1, gc3);
		btnmap_D2WP1.addActionListener(new ActionMapD2WP1());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D2_WP1D, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D2WP2 = new JLabel("WP 2");
		D2WP2.setBorder(b2);
		panel3.add(D2WP2, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D2_WP2X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D2_WP2Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D2WP2, gc3);
		btnmap_D2WP2.addActionListener(new ActionMapD2WP2());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D2_WP2D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D2_WP2A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D2WP3 = new JLabel("WP 3");
		D2WP3.setBorder(b2);
		panel3.add(D2WP3, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D2_WP3X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D2_WP3Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D2WP3, gc3);
		btnmap_D2WP3.addActionListener(new ActionMapD2WP3());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D2_WP3D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D2_WP3A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D2WP4 = new JLabel("WP 4");
		D2WP4.setBorder(b2);
		panel3.add(D2WP4, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D2_WP4X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D2_WP4Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D2WP4, gc3);
		btnmap_D2WP4.addActionListener(new ActionMapD2WP4());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D2_WP4D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D2_WP4A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D2WP5 = new JLabel("WP 5");
		D2WP5.setBorder(b2);
		panel3.add(D2WP5, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D2_WP5X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D2_WP5Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D2WP5, gc3);
		btnmap_D2WP5.addActionListener(new ActionMapD2WP5());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D2_WP5D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D2_WP5A, gc3);


		
		
		
		currentCol += 8;
		currentRow = startRow;
		
		gc3.gridwidth = 6;
		
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel dungeonL3 = new JLabel("Ivory Tower Catacomb 2");
		dungeonL3.setFont(new Font("Arial", Font.PLAIN, titleSize));
		dungeonL3.setBorder(b2);
		panel3.add(dungeonL3, gc3);
		
		currentRow++;
		
	    gc3.gridwidth = 1;
	    
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D3WP1 = new JLabel("WP 1");
		D3WP1.setBorder(b2);
		panel3.add(D3WP1, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D3_WP1X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D3_WP1Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D3WP1, gc3);
		btnmap_D3WP1.addActionListener(new ActionMapD3WP1());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D3_WP1D, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D3WP2 = new JLabel("WP 2");
		D3WP2.setBorder(b2);
		panel3.add(D3WP2, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D3_WP2X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D3_WP2Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D3WP2, gc3);
		btnmap_D3WP2.addActionListener(new ActionMapD3WP2());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D3_WP2D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D3_WP2A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D3WP3 = new JLabel("WP 3");
		D3WP3.setBorder(b2);
		panel3.add(D3WP3, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D3_WP3X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D3_WP3Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D3WP3, gc3);
		btnmap_D3WP3.addActionListener(new ActionMapD3WP3());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D3_WP3D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D3_WP3A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D3WP4 = new JLabel("WP 4");
		D3WP4.setBorder(b2);
		panel3.add(D3WP4, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D3_WP4X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D3_WP4Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D3WP4, gc3);
		btnmap_D3WP4.addActionListener(new ActionMapD3WP4());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D3_WP4D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D3_WP4A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D3WP5 = new JLabel("WP 5");
		D3WP5.setBorder(b2);
		panel3.add(D3WP5, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D3_WP5X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D3_WP5Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D3WP5, gc3);
		btnmap_D3WP5.addActionListener(new ActionMapD3WP5());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D3_WP5D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D3_WP5A, gc3);

		startRow += 6;
		
		gc3.gridwidth = llspan;
		gc3.gridx = 0;
		gc3.gridy = startRow;
		JLabel labelLine2 = new JLabel(long_line);
		panel3.add(labelLine2, gc3);
		
	
		startRow++;
		
		currentCol = 0;
		currentRow = startRow;
		
		gc3.gridwidth = 6;
		
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel dungeonL4 = new JLabel("Ivory Tower Catacomb 3");
		dungeonL4.setFont(new Font("Arial", Font.PLAIN, titleSize));
		dungeonL4.setBorder(b2);
		panel3.add(dungeonL4, gc3);
		
		currentRow++;
		
	    gc3.gridwidth = 1;
	    
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D4WP1 = new JLabel("WP 1");
		D4WP1.setBorder(b2);
		panel3.add(D4WP1, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D4_WP1X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D4_WP1Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D4WP1, gc3);
		btnmap_D4WP1.addActionListener(new ActionMapD4WP1());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D4_WP1D, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D4WP2 = new JLabel("WP 2");
		D4WP2.setBorder(b2);
		panel3.add(D4WP2, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D4_WP2X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D4_WP2Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D4WP2, gc3);
		btnmap_D4WP2.addActionListener(new ActionMapD4WP2());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D4_WP2D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D4_WP2A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D4WP3 = new JLabel("WP 3");
		D4WP3.setBorder(b2);
		panel3.add(D4WP3, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D4_WP3X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D4_WP3Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D4WP3, gc3);
		btnmap_D4WP3.addActionListener(new ActionMapD4WP3());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D4_WP3D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D4_WP3A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D4WP4 = new JLabel("WP 4");
		D4WP4.setBorder(b2);
		panel3.add(D4WP4, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D4_WP4X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D4_WP4Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D4WP4, gc3);
		btnmap_D4WP4.addActionListener(new ActionMapD4WP4());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D4_WP4D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D4_WP4A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D4WP5 = new JLabel("WP 5");
		D4WP5.setBorder(b2);
		panel3.add(D4WP5, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D4_WP5X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D4_WP5Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D4WP5, gc3);
		btnmap_D4WP5.addActionListener(new ActionMapD4WP5());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D4_WP5D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D4_WP5A, gc3);

		

		currentCol += 8;
		currentRow = startRow;
		
		gc3.gridwidth = 6;
		
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel dungeonL5 = new JLabel("Forest of Secrets Canopy");
		dungeonL5.setFont(new Font("Arial", Font.PLAIN, titleSize));
		dungeonL5.setBorder(b2);
		panel3.add(dungeonL5, gc3);
		
		currentRow++;
		
	    gc3.gridwidth = 1;
	    
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D5WP1 = new JLabel("WP 1");
		D5WP1.setBorder(b2);
		panel3.add(D5WP1, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D5_WP1X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D5_WP1Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D5WP1, gc3);
		btnmap_D5WP1.addActionListener(new ActionMapD5WP1());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D5_WP1D, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D5WP2 = new JLabel("WP 2");
		D5WP2.setBorder(b2);
		panel3.add(D5WP2, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D5_WP2X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D5_WP2Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D5WP2, gc3);
		btnmap_D5WP2.addActionListener(new ActionMapD5WP2());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D5_WP2D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D5_WP2A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D5WP3 = new JLabel("WP 3");
		D5WP3.setBorder(b2);
		panel3.add(D5WP3, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D5_WP3X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D5_WP3Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D5WP3, gc3);
		btnmap_D5WP3.addActionListener(new ActionMapD5WP3());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D5_WP3D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D5_WP3A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D5WP4 = new JLabel("WP 4");
		D5WP4.setBorder(b2);
		panel3.add(D5WP4, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D5_WP4X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D5_WP4Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D5WP4, gc3);
		btnmap_D5WP4.addActionListener(new ActionMapD5WP4());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D5_WP4D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D5_WP4A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D5WP5 = new JLabel("WP 5");
		D5WP5.setBorder(b2);
		panel3.add(D5WP5, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D5_WP5X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D5_WP5Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D5WP5, gc3);
		btnmap_D5WP5.addActionListener(new ActionMapD5WP5());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D5_WP5D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D5_WP5A, gc3);


		
		currentCol += 8;
		currentRow = startRow;
		
		gc3.gridwidth = 6;
		
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel dungeonL6 = new JLabel("Forest of Secrets Understory");
		dungeonL6.setFont(new Font("Arial", Font.PLAIN, titleSize));
		dungeonL6.setBorder(b2);
		panel3.add(dungeonL6, gc3);
		
		currentRow++;
		
	    gc3.gridwidth = 1;
	    
		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D6WP1 = new JLabel("WP 1");
		D6WP1.setBorder(b2);
		panel3.add(D6WP1, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D6_WP1X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D6_WP1Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D6WP1, gc3);
		btnmap_D6WP1.addActionListener(new ActionMapD6WP1());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D6_WP1D, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D6WP2 = new JLabel("WP 2");
		D6WP2.setBorder(b2);
		panel3.add(D6WP2, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D6_WP2X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D6_WP2Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D6WP2, gc3);
		btnmap_D6WP2.addActionListener(new ActionMapD6WP2());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D6_WP2D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D6_WP2A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D6WP3 = new JLabel("WP 3");
		D6WP3.setBorder(b2);
		panel3.add(D6WP3, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D6_WP3X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D6_WP3Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D6WP3, gc3);
		btnmap_D6WP3.addActionListener(new ActionMapD6WP3());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D6_WP3D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D6_WP3A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D6WP4 = new JLabel("WP 4");
		D6WP4.setBorder(b2);
		panel3.add(D6WP4, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D6_WP4X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D6_WP4Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D6WP4, gc3);
		btnmap_D6WP4.addActionListener(new ActionMapD6WP4());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D6_WP4D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D6_WP4A, gc3);

		currentRow++;

		gc3.gridx = currentCol+1;
		gc3.gridy = currentRow;
		JLabel D6WP5 = new JLabel("WP 5");
		D6WP5.setBorder(b2);
		panel3.add(D6WP5, gc3);

		gc3.gridx = currentCol+2;
		gc3.gridy = currentRow;
		panel3.add(D6_WP5X, gc3);

		gc3.gridx = currentCol+3;
		gc3.gridy = currentRow;
		panel3.add(D6_WP5Y, gc3);

		gc3.gridx = currentCol+4;
		gc3.gridy = currentRow;
		panel3.add(btnmap_D6WP5, gc3);
		btnmap_D6WP5.addActionListener(new ActionMapD6WP5());

		gc3.gridx = currentCol+5;
		gc3.gridy = currentRow;
		panel3.add(D6_WP5D, gc3);

		gc3.gridx = currentCol+6;
		gc3.gridy = currentRow;
		panel3.add(D6_WP5A, gc3);

		startRow += 6;
		
		gc3.gridwidth = llspan;
		gc3.gridx = 0;
		gc3.gridy = startRow;
		JLabel labelLine3 = new JLabel(long_line);
		panel3.add(labelLine3, gc3);

		startRow++;
		
		gc3.gridx = 20;
		gc3.gridy = startRow;
		panel3.add(btn_AcceptSettings, gc3);
		btn_AcceptSettings.addActionListener(new Action_AcceptSettings());
		
		frame3.pack();
		
		frameMap = new JDialog(frame3, "Map", true);
		JPanel panelMap = new JPanel(new GridLayout(0, 1));
		frameMap.getContentPane().add(panelMap);
		frameMap.setVisible(false);
		frameMap.setSize(mapsize, mapsize);
		frameMap.setLocationRelativeTo( null );
		panelMap.setPreferredSize(new Dimension(mapsize, mapsize));
		panelMap.setLayout(new GridBagLayout());
		
		panelMap.add(label_map_image);
		
		panelMap.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	mouseX=e.getX();
		    	mouseY=e.getY();
		    	
		    	float cx = 0f;
		    	float cy = 0f;
		    	
		    	if (lastMap == "H1" || lastMap == "H2" || lastMap == "H3") {
		    		cx = herbs_map_diff_x+mouseX-map_x_diff/2;
		    		cy = herbs_map_diff_y+mouseY-map_y_diff/2;
		    	}

		    	if (lastMap == "D1WP1" || lastMap == "D1WP2" || lastMap == "D1WP3" || lastMap == "D1WP4" || lastMap == "D1WP5") {
		    		cx = dungeon_map_cruma3_diff_x+mouseX-map_x_diff/2;
		    		cy = dungeon_map_cruma3_diff_y+mouseY-map_y_diff/2;
		    	}
		    	if (lastMap == "D2WP1" || lastMap == "D2WP2" || lastMap == "D2WP3" || lastMap == "D2WP4" || lastMap == "D2WP5") {
		    		cx = dungeon_map_ivory1_diff_x+mouseX-map_x_diff/2;
		    		cy = dungeon_map_ivory1_diff_y+mouseY-map_y_diff/2;
		    	}
		    	if (lastMap == "D3WP1" || lastMap == "D3WP2" || lastMap == "D3WP3" || lastMap == "D3WP4" || lastMap == "D3WP5") {
		    		cx = dungeon_map_ivory2_diff_x+mouseX-map_x_diff/2;
		    		cy = dungeon_map_ivory2_diff_y+mouseY-map_y_diff/2;
		    	}
		    	if (lastMap == "D4WP1" || lastMap == "D4WP2" || lastMap == "D4WP3" || lastMap == "D4WP4" || lastMap == "D4WP5") {
		    		cx = dungeon_map_ivory3_diff_x+mouseX-map_x_diff/2;
		    		cy = dungeon_map_ivory3_diff_y+mouseY-map_y_diff/2;
		    	}
		    	if (lastMap == "D5WP1" || lastMap == "D5WP2" || lastMap == "D5WP3" || lastMap == "D5WP4" || lastMap == "D5WP5") {
		    		cx = dungeon_map_foscan_diff_x+mouseX-map_x_diff/2;
		    		cy = dungeon_map_foscan_diff_y+mouseY-map_y_diff/2;
		    	}
		    	if (lastMap == "D6WP1" || lastMap == "D6WP2" || lastMap == "D6WP3" || lastMap == "D6WP4" || lastMap == "D6WP5") {
		    		cx = dungeon_map_fosund_diff_x+mouseX-map_x_diff/2;
		    		cy = dungeon_map_fosund_diff_y+mouseY-map_y_diff/2;
		    	}

		    	if (lastMap == "H1") {
		    		coordHerbs1X.setText(String.format("%.0f", cx));
		    		coordHerbs1Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "H2") {
		    		coordHerbs2X.setText(String.format("%.0f", cx));
		    		coordHerbs2Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "H3") {
		    		coordHerbs3X.setText(String.format("%.0f", cx));
		    		coordHerbs3Y.setText(String.format("%.0f", cy));
		    	}
		    	
		    	if (lastMap == "D1WP1") {
		    		D1_WP1X.setText(String.format("%.0f", cx));
		    		D1_WP1Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D1WP2") {
		    		D1_WP2X.setText(String.format("%.0f", cx));
		    		D1_WP2Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D1WP3") {
		    		D1_WP3X.setText(String.format("%.0f", cx));
		    		D1_WP3Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D1WP4") {
		    		D1_WP4X.setText(String.format("%.0f", cx));
		    		D1_WP4Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D1WP5") {
		    		D1_WP5X.setText(String.format("%.0f", cx));
		    		D1_WP5Y.setText(String.format("%.0f", cy));
		    	}

		    	if (lastMap == "D2WP1") {
		    		D2_WP1X.setText(String.format("%.0f", cx));
		    		D2_WP1Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D2WP2") {
		    		D2_WP2X.setText(String.format("%.0f", cx));
		    		D2_WP2Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D2WP3") {
		    		D2_WP3X.setText(String.format("%.0f", cx));
		    		D2_WP3Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D2WP4") {
		    		D2_WP4X.setText(String.format("%.0f", cx));
		    		D2_WP4Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D2WP5") {
		    		D2_WP5X.setText(String.format("%.0f", cx));
		    		D2_WP5Y.setText(String.format("%.0f", cy));
		    	}

		    	if (lastMap == "D3WP1") {
		    		D3_WP1X.setText(String.format("%.0f", cx));
		    		D3_WP1Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D3WP2") {
		    		D3_WP2X.setText(String.format("%.0f", cx));
		    		D3_WP2Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D3WP3") {
		    		D3_WP3X.setText(String.format("%.0f", cx));
		    		D3_WP3Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D3WP4") {
		    		D3_WP4X.setText(String.format("%.0f", cx));
		    		D3_WP4Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D3WP5") {
		    		D3_WP5X.setText(String.format("%.0f", cx));
		    		D3_WP5Y.setText(String.format("%.0f", cy));
		    	}

		    	if (lastMap == "D4WP1") {
		    		D4_WP1X.setText(String.format("%.0f", cx));
		    		D4_WP1Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D4WP2") {
		    		D4_WP2X.setText(String.format("%.0f", cx));
		    		D4_WP2Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D4WP3") {
		    		D4_WP3X.setText(String.format("%.0f", cx));
		    		D4_WP3Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D4WP4") {
		    		D4_WP4X.setText(String.format("%.0f", cx));
		    		D4_WP4Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D4WP5") {
		    		D4_WP5X.setText(String.format("%.0f", cx));
		    		D4_WP5Y.setText(String.format("%.0f", cy));
		    	}

		    	if (lastMap == "D5WP1") {
		    		D5_WP1X.setText(String.format("%.0f", cx));
		    		D5_WP1Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D5WP2") {
		    		D5_WP2X.setText(String.format("%.0f", cx));
		    		D5_WP2Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D5WP3") {
		    		D5_WP3X.setText(String.format("%.0f", cx));
		    		D5_WP3Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D5WP4") {
		    		D5_WP4X.setText(String.format("%.0f", cx));
		    		D5_WP4Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D5WP5") {
		    		D5_WP5X.setText(String.format("%.0f", cx));
		    		D5_WP5Y.setText(String.format("%.0f", cy));
		    	}

		    	if (lastMap == "D6WP1") {
		    		D6_WP1X.setText(String.format("%.0f", cx));
		    		D6_WP1Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D6WP2") {
		    		D6_WP2X.setText(String.format("%.0f", cx));
		    		D6_WP2Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D6WP3") {
		    		D6_WP3X.setText(String.format("%.0f", cx));
		    		D6_WP3Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D6WP4") {
		    		D6_WP4X.setText(String.format("%.0f", cx));
		    		D6_WP4Y.setText(String.format("%.0f", cy));
		    	}
		    	if (lastMap == "D6WP5") {
		    		D6_WP5X.setText(String.format("%.0f", cx));
		    		D6_WP5Y.setText(String.format("%.0f", cy));
		    	}
		    	
		    	frameMap.setVisible(false);
		    }  
		});
		
		frameMap.pack();
		
		
		frame4 = new JDialog(frame3, "AutoConfig", true);
		JPanel panel4 = new JPanel();
		frame4.getContentPane().add(panel4);
		frame4.pack();
		frame4.setVisible(false);
        frame4.setSize(1300, 700);
		frame4.setLocationRelativeTo( null );
		
		panel4.setLayout(new GridBagLayout());
		
		GridBagConstraints gc4 = new GridBagConstraints();
		
		gc4.anchor = GridBagConstraints.CENTER;
		gc4.fill = GridBagConstraints.BOTH;
		
		Font acfont = new Font("Arial", Font.PLAIN, 34);
		
		JLabel ac_infoText = new JLabel("Move and Scale this window to cover the Emulator exactly!");
		gc4.gridx = 0;
		gc4.gridy = 0;
		gc4.gridwidth = 5;
		ac_infoText.setFont(acfont);
		panel4.add(ac_infoText, gc4);
		
		gc4.gridwidth = 1;

		JLabel ac_label_screen = new JLabel("Screen:");
		gc4.gridx = 0;
		gc4.gridy = 1;
		ac_label_screen.setFont(acfont);
		panel4.add(ac_label_screen, gc4);
		gc4.gridx = 1;
		gc4.gridy = 1;
		ac_screen.setFont(acfont);
		panel4.add(ac_screen, gc4);

		
		JLabel ac_label_scale = new JLabel("Scale:");
		gc4.gridx = 0;
		gc4.gridy = 2;
		ac_label_scale.setFont(acfont);
		panel4.add(ac_label_scale, gc4);
		gc4.gridx = 1;
		gc4.gridy = 2;
		ac_scale.setFont(acfont);
		panel4.add(ac_scale, gc4);
		
		JLabel ac_label_offsetx = new JLabel("Offset X:");
		gc4.gridx = 0;
		gc4.gridy = 3;
		ac_label_offsetx.setFont(acfont);
		panel4.add(ac_label_offsetx, gc4);
		gc4.gridx = 1;
		gc4.gridy = 3;
		ac_offsetx.setFont(acfont);
		panel4.add(ac_offsetx, gc4);
		
		JLabel ac_label_offsety = new JLabel("Offset Y:");
		gc4.gridx = 0;
		gc4.gridy = 4;
		ac_label_offsety.setFont(acfont);
		panel4.add(ac_label_offsety, gc4);
		gc4.gridx = 1;
		gc4.gridy = 4;
		ac_offsety.setFont(acfont);
		panel4.add(ac_offsety, gc4);
/*		
		gc4.gridx = 0;
		gc4.gridy = 5;
		JButton autoConfigApply = new JButton("Apply");
		autoConfigApply.setFont(acfont);
		panel4.add(autoConfigApply, gc4);
		autoConfigApply.addActionListener(new Action_AutoConfig_Apply());
*/
		gc4.gridx = 0;
		gc4.gridy = 5;
		JButton autoConfigApplyTest = new JButton("Apply & Test");
		autoConfigApplyTest.setFont(acfont);
		panel4.add(autoConfigApplyTest, gc4);
		autoConfigApplyTest.addActionListener(new Action_AutoConfig_ApplyTest());
		
		frame4.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		    	updateAC();
		    }
		    public void componentMoved(ComponentEvent componentEvent) {
		    	updateAC();
		    }
		});
		
		setari.MoveMouseDelay = 0f;
		
		lockButtons();
		log("Initializing L2RBot 5000..");
		loadSettings();
		setupScreens();
		log("Init - screen setup done..");
		setupGraphics();
		log("Init - graphic elements rescaled..");
		
		emulatorList.addActionListener(new ActionEmuList());
		resolutionList.addActionListener(new ActionResList());
		dungeonListDA.addActionListener(new ActionDADungeonSel());
		dungeonListDR.addActionListener(new ActionDRDungeonSel());
		
		unlockButtons();
		
		log("Init - complete.");
		
        if (args.length > 0 && args[0].equals("auto")) {
        	goClick2(414, 654);
        	Sleep2(1000);
        	bbs.type(Key.ENTER);
        	log("Starting Bluestacks..");
        	Sleep2(20000);
        	goClick2(1856, 30);
        	Sleep2(5000);
        	lfac_nr(AUTO_START_MYAPPS, ACCURACY);
        	Sleep2(3000);
        	lfac_nr(AUTO_START_L2, ACCURACY);
        	Sleep2(3000);
        	log("Starting L2R..");
        	Sleep2(40000);
        	goClick(955, 955);
        	log("MM Tap 1..");
        	Sleep2(10000);
        	goClick(955, 955);
        	log("MM Tap 2..");
        	Sleep2(10000);
        	goClick(955, 955);
        	if (bbs.exists(UPDATE_OK.similar(ACCURACY)) != null) {
        		lfac_nr(UPDATE_OK, ACCURACY);
            	log("Update running..");
        		Sleep2(30000);
            	goClick(1868, 76);
        		Sleep2(30000);
            	goClick(955, 955);
            	log("MM Tap 1..");
            	Sleep2(10000);
        	}
        	
        	boolean done = false;
        	while (!done) {
        		if (bbs.exists(AUTO_START_CLOSE.similar(ACC_HIGH)) != null) {
            		lfac_nr(AUTO_START_CLOSE, ACC_HIGH);
            		log("Closing Add ..");
                	Sleep2(1000);
        		} else
        		if (bbs.exists(AUTO_START_PLAY.similar(ACCURACY)) != null) {
            		lfac_nr(AUTO_START_PLAY, ACCURACY);
            		log("GAME START");
            		done = true;
        		} else {
                	goClick(955, 955);
                	log("MM Tap ..");
                	Sleep2(5000);
        		}
        		Sleep2(2000);
        	}
        	
        	
        	Sleep2(45000);
        	if (bbs.exists(REWARD_X.similar(ACCURACY)) != null) {
        		lfac_nr(REWARD_X, ACCURACY);
        		Sleep2(2000);
        	}
        	doItAll();
        	
        }

	}
	
	static void updateAC() {
		

    	Rectangle r = frame4.getBounds();
    	float newx = r.x+8;
    	if (newx > 1920) {
    		ac_screen.setText("1");
    		newx = newx-1920;
    	} else {
    		ac_screen.setText("0");
    	}
    	
    	int ox = Math.round(newx);
    	int oy = Math.round(r.y);
    	
    	Double calcscale = (double) r.width * 100 / 1914;
    	Double rscale = Math.round(calcscale * 10) / 10.0;
    	ac_scale.setText(Double.toString(rscale));
    	ac_offsetx.setText(Integer.toString(ox));
    	ac_offsety.setText(Integer.toString(oy));
	}

	
	static void setupScreens() {
		try {
			int screen = Integer.parseInt(setupScreen.getText());
			bbs = new Screen(screen);
			if (screen == 1) DEFX = 1920; else DEFX = 0;
	        frame2.setLocation(DEFX+300, 0); //INFOBOX LOCATION
		} catch (NumberFormatException ex) {
			log("--- Corrupt Config File! Close program and then delete it!");
		}
	}
	
	static void setupGraphics() {
		
		
		AUTO_START_MYAPPS = scaledImageLocal(STR_AUTO_START_MYAPPS);
		AUTO_START_L2 = scaledImageLocal(STR_AUTO_START_L2);
		AUTO_START_CLOSE = scaledImageLocal(STR_AUTO_START_CLOSE);
		AUTO_START_PLAY = scaledImageLocal(STR_AUTO_START_PLAY);
		
		MQ_CLAIM_REWARD = scaledImageLocal(STR_MQ_CLAIM_REWARD);
		MQ_CLAIM_REWARD = scaledImageLocal(STR_MQ_CLAIM_REWARD);
		MQ_ACCEPT_QUEST = scaledImageLocal(STR_MQ_ACCEPT_QUEST);
		MQ_SPOT_REVIVAL = scaledImageLocal(STR_MQ_SPOT_REVIVAL);
		MQ_DEATH_X = scaledImageLocal(STR_MQ_DEATH_X);
		MQ_WALK_TOWARDS = scaledImageLocal(STR_MQ_WALK_TOWARDS);
		MQ_QUEST_ARROWS = scaledImageLocal(STR_MQ_QUEST_ARROWS);
		MQ_QUEST_SKIP = scaledImageLocal(STR_MQ_QUEST_SKIP);
		MQ_BOX_USE = scaledImageLocal(STR_MQ_BOX_USE);
		MQ_CONTINUE = scaledImageLocal(STR_MQ_CONTINUE);
		MQ_LOCKED = scaledImageLocal(STR_MQ_LOCKED);
		MQ_LOCKED2 = scaledImageLocal(STR_MQ_LOCKED);
		
		QUESTLOG_START = scaledImageLocal(STR_QUESTLOG_START);
		WEEKLY_QUESTS = scaledImageLocal(STR_WEEKLY_QUESTS);
		WEEKLY_DONE = scaledImageLocal(STR_WEEKLY_DONE);
		WEEKLY_QUEST_COMPLETE = scaledImageLocal(STR_WEEKLY_QUEST_COMPLETE);
		WEEKLY_START_QUEST = scaledImageLocal(STR_WEEKLY_START_QUEST);
		WEEKLY_GO_NOW = scaledImageLocal(STR_WEEKLY_GO_NOW);
		WEEKLY_WALK = scaledImageLocal(STR_WEEKLY_WALK);
		WEEKLY_COMPLETE = scaledImageLocal(STR_WEEKLY_COMPLETE);
		PAGE_IS_DAILY_QUESTS = scaledImageLocal(STR_PAGE_IS_DAILY_QUESTS);

		DAILY_CLAIM_REWARD = scaledImageLocal(STR_DAILY_CLAIM_REWARD);
		DAILY_COMPLETE_W_COIN = scaledImageLocal(STR_DAILY_COMPLETE_W_COIN);
		DAILY_R = scaledImageLocal(STR_DAILY_R);
		DAILY_S = scaledImageLocal(STR_DAILY_S);
		DAILY_REFRESH = scaledImageLocal(STR_DAILY_REFRESH);
		DAILY_DONE = scaledImageLocal(STR_DAILY_DONE);
		DAILY_QUESTS = scaledImageLocal(STR_DAILY_QUESTS);
		DAILY_AVAILABLE = scaledImageLocal(STR_DAILY_AVAILABLE);
		DAILY_REWARD1 = scaledImageLocal(STR_DAILY_REWARD1);
		DAILY_REWARD2 = scaledImageLocal(STR_DAILY_REWARD2);
		DAILY_COMPLETED = scaledImageLocal(STR_DAILY_COMPLETED);

		SUBQ_AVAIL = scaledImageLocal(STR_SUBQ_AVAIL);
		SUBQ_NOTAVAIL = scaledImageLocal(STR_SUBQ_NOTAVAIL);
		SUBQ_A = scaledImageLocal(STR_SUBQ_A);
		SUBQ_B = scaledImageLocal(STR_SUBQ_B);
		SUBQ_X = scaledImageLocal(STR_SUBQ_X);
		SUBQ_FF = scaledImageLocal(STR_SUBQ_FF);
		SUBQ_OK = scaledImageLocal(STR_SUBQ_OK);
		SUBQ_START = scaledImageLocal(STR_SUBQ_START);
		SUBQ_CONT = scaledImageLocal(STR_SUBQ_CONT);
		SUBQ_SHOP = scaledImageLocal(STR_SUBQ_SHOP);
		SUBQ_SHOP2 = scaledImageLocal(STR_SUBQ_SHOP2);
		SUBQ_SCROLL = scaledImageLocal(STR_SUBQ_SCROLL);
		SUBQ_BUYSCROLL = scaledImageLocal(STR_SUBQ_BUYSCROLL);
		SUBQ_BUYSCROLL_BUY = scaledImageLocal(STR_SUBQ_BUYSCROLL_BUY);
		SUBQ_SOLD_OUT = scaledImageLocal(STR_SUBQ_SOLD_OUT);
		SUBQ_RESET = scaledImageLocal(STR_SUBQ_RESET);
		SUBQ_RESET_COUNT50 = scaledImageLocal(STR_SUBQ_RESET_COUNT50);
		SUBQ_RESET_RESET = scaledImageLocal(STR_SUBQ_RESET_RESET);
		SUBQ_RESET_CANCEL = scaledImageLocal(STR_SUBQ_RESET_CANCEL);

		EXIT = scaledImageLocal(STR_EXIT);

		DAILY_ACT = scaledImageLocal(STR_DAILY_ACT);
		DA_SCREEN = scaledImageLocal(STR_DA_SCREEN);
		DA_CLAIM_REWARD = scaledImageLocal(STR_DA_CLAIM_REWARD);

		DA_REW1 = scaledImageLocal(STR_DA_REW1);
		DA_REW2 = scaledImageLocal(STR_DA_REW2);
		DA_REW3 = scaledImageLocal(STR_DA_REW3);

		DA_FREE_DRAW = scaledImageLocal(STR_DA_FREE_DRAW);
		DA_BAD_IMAGE_X = scaledImageLocal(STR_DA_BAD_IMAGE_X);
		DA_FREE_DRAW_FP = scaledImageLocal(STR_DA_FREE_DRAW_FP);
		DA_FREE_BOX = scaledImageLocal(STR_DA_FREE_BOX);
		DA_FREE_BOX_OPEN = scaledImageLocal(STR_DA_FREE_BOX_OPEN);
		DA_FREE_BOX_OPEN_OK = scaledImageLocal(STR_DA_FREE_BOX_OPEN_OK);

		DA_TOI = scaledImageLocal(STR_DA_TOI);
		DA_TOI_AC = scaledImageLocal(STR_DA_TOI_AC);
		DA_TOI_AC_OK = scaledImageLocal(STR_DA_TOI_AC_OK);

		DA_FRIENDS = scaledImageLocal(STR_DA_FRIENDS);
		DA_FRIENDS_GA = scaledImageLocal(STR_DA_FRIENDS_GA);
		DA_FRIENDS_CA = scaledImageLocal(STR_DA_FRIENDS_CA);
		DA_FRIENDS_OK = scaledImageLocal(STR_DA_FRIENDS_OK);
		 
		DA_DD = scaledImageLocal(STR_DA_DD);
		DA_DD_ENTER = scaledImageLocal(STR_DA_DD_ENTER);
		DA_DD_OK = scaledImageLocal(STR_DA_DD_OK);

		DA_CLAN = scaledImageLocal(STR_DA_CLAN);
		DA_CLAN_DONATION = scaledImageLocal(STR_DA_CLAN_DONATION);
		DA_CLAN_CHECKIN = scaledImageLocal(STR_DA_CLAN_CHECKIN);
		DA_CLAN_CHECKIN_REWARD = scaledImageLocal(STR_DA_CLAN_CHECKIN_REWARD);
		DA_CLAN_CHECKIN_REWARD_OK = scaledImageLocal(STR_DA_CLAN_CHECKIN_REWARD_OK);

		DA_CLAN_MEMBERS = scaledImageLocal(STR_DA_CLAN_MEMBERS);
		DA_CLAN_MEMBERS_GA = scaledImageLocal(STR_DA_CLAN_MEMBERS_GA);
		DA_CLAN_MEMBERS_CA = scaledImageLocal(STR_DA_CLAN_MEMBERS_CA);
		DA_CLAN_MEMBERS_OK = scaledImageLocal(STR_DA_CLAN_MEMBERS_OK);
		DA_CLAN_MEMBERS_INFO = scaledImageLocal(STR_DA_CLAN_MEMBERS_INFO);

		DA_CLAN_DONATEBTN = scaledImageLocal(STR_DA_CLAN_DONATEBTN);
		DA_CLAN_DONATEBTN2 = scaledImageLocal(STR_DA_CLAN_DONATEBTN2);
		DA_CLAN_DONATE_SLIDER = scaledImageLocal(STR_DA_CLAN_DONATE_SLIDER);
		DA_CLAN_DONATE_PLUS = scaledImageLocal(STR_DA_CLAN_DONATE_PLUS);
		DA_CLAN_DONATE_SUBMIT1 = scaledImageLocal(STR_DA_CLAN_DONATE_SUBMIT1);
		DA_CLAN_DONATE_SUBMIT2 = scaledImageLocal(STR_DA_CLAN_DONATE_SUBMIT2);
		DA_CLAN_DONATE_SUBMIT3 = scaledImageLocal(STR_DA_CLAN_DONATE_SUBMIT3);
		DA_CLAN_DONATE_TAB2 = scaledImageLocal(STR_DA_CLAN_DONATE_TAB2);
		DA_CLAN_DONATE_TAB3 = scaledImageLocal(STR_DA_CLAN_DONATE_TAB3);
		DA_CLAN_DONATE_REWARD = scaledImageLocal(STR_DA_CLAN_DONATE_REWARD);
		DA_CLAN_NE = scaledImageLocal(STR_DA_CLAN_NE);
		DA_CLAN_NEX = scaledImageLocal(STR_DA_CLAN_NEX);

		DA_CLAN_SHOP = scaledImageLocal(STR_DA_CLAN_SHOP);
		DA_CLAN_SHOP_GX = scaledImageLocal(STR_DA_CLAN_SHOP_GX);
		DA_CLAN_SHOP_GX_BUY = scaledImageLocal(STR_DA_CLAN_SHOP_GX_BUY);
		DA_CLAN_SHOP_GX_BUY_OK = scaledImageLocal(STR_DA_CLAN_SHOP_GX_BUY_OK);
		DA_CLAN_SHOP_GX_BUY_KO = scaledImageLocal(STR_DA_CLAN_SHOP_GX_BUY_KO);
		DA_CLAN_SHOP_GX_BUY_KO_X = scaledImageLocal(STR_DA_CLAN_SHOP_GX_BUY_KO_X);

		DA_ARENA = scaledImageLocal(STR_DA_ARENA);
		DA_ARENA_CLAIM_REWARD = scaledImageLocal(STR_DA_ARENA_CLAIM_REWARD);
		DA_ARENA_CLAIM_REWARD_OK = scaledImageLocal(STR_DA_ARENA_CLAIM_REWARD_OK);
		DA_ARENA_CP1 = scaledImageLocalC(STR_DA_ARENA_CP1);
		DA_ARENA_CP2 = scaledImageLocalC(STR_DA_ARENA_CP2);
		DA_ARENA_CP3 = scaledImageLocalC(STR_DA_ARENA_CP3);
		DA_ARENA_CP4 = scaledImageLocalC(STR_DA_ARENA_CP4);
		DA_ARENA_CP5 = scaledImageLocalC(STR_DA_ARENA_CP5);
		DA_ARENA_REFRESH = scaledImageLocal(STR_DA_ARENA_REFRESH);
		DA_ARENA_OK = scaledImageLocal(STR_DA_ARENA_OK);
		DA_ARENA_RANK_INCREASED = scaledImageLocal(STR_DA_ARENA_RANK_INCREASED); 
		
		DA_COMPLETE = scaledImageLocal(STR_DA_COMPLETE);
		
		DA_ELITE_DUNGEON = scaledImageLocal(STR_DA_ELITE_DUNGEON);
		DA_ELITE_DUNGEON_CRUMA3 = scaledImageLocal(STR_DA_ELITE_DUNGEON_CRUMA3);
		DA_ELITE_DUNGEON_IVORY1 = scaledImageLocal(STR_DA_ELITE_DUNGEON_IVORY1);
		DA_ELITE_DUNGEON_IVORY2 = scaledImageLocal(STR_DA_ELITE_DUNGEON_IVORY2);
		DA_ELITE_DUNGEON_IVORY3 = scaledImageLocal(STR_DA_ELITE_DUNGEON_IVORY3);
		DA_ELITE_DUNGEON_FOS_CANOPY = scaledImageLocal(STR_DA_ELITE_DUNGEON_FOS_CANOPY);
		DA_ELITE_DUNGEON_FOS_UNDER = scaledImageLocal(STR_DA_ELITE_DUNGEON_FOS_UNDER);
		
		DA_ELITE_DUNGEON_AUTOCLEAR = scaledImageLocal(STR_DA_ELITE_DUNGEON_AUTOCLEAR);
		DA_ELITE_DUNGEON_AUTOCLEAR_OK = scaledImageLocal(STR_DA_ELITE_DUNGEON_AUTOCLEAR_OK);
		DA_ELITE_NOT_ENOUGH_DIAMONDS = scaledImageLocal(STR_DA_ELITE_NOT_ENOUGH_DIAMONDS);
		DA_ELITE_NOT_ENOUGH_DIAMONDS_X = scaledImageLocal(STR_DA_ELITE_NOT_ENOUGH_DIAMONDS_X);

		DA_HERBS = scaledImageLocal(STR_DA_HERBS);
		DA_HERBS_ENTER = scaledImageLocal(STR_DA_HERBS_ENTER);
		DA_MAP_X = scaledImageLocal(STR_DA_MAP_X);
		DA_HERBS_GATHER_BTN = scaledImageLocal(STR_DA_HERBS_GATHER_BTN);
		DA_HERBS_COMPLETE = scaledImageLocal(STR_DA_HERBS_COMPLETE);
		DA_DUNGEON_EXIT_CONF = scaledImageLocal(STR_DA_DUNGEON_EXIT_CONF);
		DA_DUNGEON_OK = scaledImageLocal(STR_DA_DUNGEON_OK);

		DA_TEMPLE = scaledImageLocal(STR_DA_TEMPLE);
		DA_TEMPLE_PARTY = scaledImageLocal(STR_DA_TEMPLE_PARTY);
		DA_TEMPLE_INPARTY = scaledImageLocal(STR_DA_TEMPLE_INPARTY);
		DA_TEMPLE_INPARTY_OK = scaledImageLocal(STR_DA_TEMPLE_INPARTY_OK);
		DA_TEMPLE_OK = scaledImageLocal(STR_DA_TEMPLE_OK);

		DA_VAULT = scaledImageLocal(STR_DA_VAULT);
		DA_VAULT_AC = scaledImageLocal(STR_DA_VAULT_AC);
		DA_VAULT_AC_GO = scaledImageLocal(STR_DA_VAULT_AC_GO);
		DA_VAULT_AC_OK = scaledImageLocal(STR_DA_VAULT_AC_OK);
		DA_VAULT_AC_BACK = scaledImageLocal(STR_DA_VAULT_AC_BACK);
		DA_VAULT_ENTER = scaledImageLocal(STR_DA_VAULT_ENTER);
		DA_VAULT_CONFIRM = scaledImageLocal(STR_DA_VAULT_CONFIRM);
		DA_VAULT_FINISHED_OK = scaledImageLocal(STR_DA_VAULT_FINISHED_OK);

		DA_TRIALS = scaledImageLocal(STR_DA_TRIALS);
		DA_TRIALS_AC = scaledImageLocal(STR_DA_TRIALS_AC);
		DA_TRIALS_AC_GO = scaledImageLocal(STR_DA_TRIALS_AC_GO);
		DA_TRIALS_AC_OK = scaledImageLocal(STR_DA_TRIALS_AC_OK);
		DA_TRIALS_AC_BACK = scaledImageLocal(STR_DA_TRIALS_AC_BACK);
		DA_TRIALS_ENTER = scaledImageLocal(STR_DA_TRIALS_ENTER);
		DA_TRIALS_CONFIRM = scaledImageLocal(STR_DA_TRIALS_CONFIRM);
		DA_TRIALS_FINISHED_OK = scaledImageLocal(STR_DA_TRIALS_FINISHED_OK);
		DA_TRIALS_DENIED = scaledImageLocal(STR_DA_TRIALS_DENIED);
		DA_TRIALS_OK = scaledImageLocal(STR_DA_TRIALS_OK);

		DA_CIRCLE = scaledImageLocal(STR_DA_CIRCLE);
		DA_CIRCLE_PARTY = scaledImageLocal(STR_DA_CIRCLE_PARTY);
		DA_CIRCLE_INPARTY = scaledImageLocal(STR_DA_CIRCLE_INPARTY);
		DA_CIRCLE_INPARTY_OK = scaledImageLocal(STR_DA_CIRCLE_INPARTY_OK);
		DA_CIRCLE_OK = scaledImageLocal(STR_DA_CIRCLE_OK);

		DA_FREE_HG = scaledImageLocal(STR_DA_FREE_HG);
		DA_FREE_HG_BOX = scaledImageLocal(STR_DA_FREE_HG_BOX);
		DA_FREE_HG_BOX_OPEN = scaledImageLocal(STR_DA_FREE_HG_BOX_OPEN);
		DA_FREE_HG_BOX_OPEN_OK = scaledImageLocal(STR_DA_FREE_HG_BOX_OPEN_OK);

		INV_SCREEN = scaledImageLocal(STR_INV_SCREEN);
		INV_POTION = scaledImageLocal(STR_INV_POTION);
		INV_RED_DOT = scaledImageLocal(STR_INV_RED_DOT);
		INV_RED_DOT_NEW = scaledImageLocal(STR_INV_RED_DOT_NEW);
		INV_S1_USE = scaledImageLocal(STR_INV_S1_USE);
		INV_S2_USE = scaledImageLocal(STR_INV_S2_USE);
		INV_S2_CHOICE_TICKET = scaledImageLocalC(STR_INV_S2_CHOICE_TICKET);
		INV_S2_CHOICE_STONE = scaledImageLocalC(STR_INV_S2_CHOICE_STONE);
		INV_S2_CHOICE_STONE2 = scaledImageLocalC(STR_INV_S2_CHOICE_STONE2);
		INV_S2_CHOICE_VARNISH = scaledImageLocalC(STR_INV_S2_CHOICE_VARNISH);
		INV_S2_CHOICE_UPGRADE = scaledImageLocalC(STR_INV_S2_CHOICE_UPGRADE);
		INV_S2_CHOICE_ENHANCE = scaledImageLocalC(STR_INV_S2_CHOICE_ENHANCE);
		INV_S2_NEXT = scaledImageLocal(STR_INV_S2_NEXT);
		INV_S3_OK = scaledImageLocal(STR_INV_S3_OK);
		INV_S4_OK = scaledImageLocal(STR_INV_S4_OK);
		INV_SLIDER = scaledImageLocal(STR_INV_SLIDER);
		INV_PLUS = scaledImageLocal(STR_INV_PLUS);
		INV_MORE = scaledImageLocal(STR_INV_MORE);

		EXIT_FROM_MENU = scaledImageLocal(STR_EXIT_FROM_MENU);
		IS_IN_MENU = scaledImageLocal(STR_IS_IN_MENU);
		GO_BACK = scaledImageLocal(STR_GO_BACK);
		PARTY_20 = scaledImageLocal(STR_PARTY_20);
		
		BTN_CLAN = scaledImageLocal(STR_BTN_CLAN);
		BTN_CLAN_HALL = scaledImageLocal(STR_BTN_CLAN_HALL);
		CH_MERCH_GIFT = scaledImageLocal(STR_CH_MERCH_GIFT);
		CH_RELIC_CHECK = scaledImageLocal(STR_CH_RELIC_CHECK);
		CH_RELIC_CHECK_OK = scaledImageLocal(STR_CH_RELIC_CHECK_OK);

		CH_RELIC_BOX1 = scaledImageLocal(STR_CH_RELIC_BOX1);
		CH_RELIC_BOX2 = scaledImageLocal(STR_CH_RELIC_BOX2);
		CH_RELIC_BOX3 = scaledImageLocal(STR_CH_RELIC_BOX3);
		CH_RELIC_REGISTER = scaledImageLocal(STR_CH_RELIC_REGISTER);

		BTN_CHALLENGES = scaledImageLocal(STR_BTN_CHALLENGES);
		BTN_CHIEVES = scaledImageLocal(STR_BTN_CHIEVES);
		BTN_CODEX = scaledImageLocal(STR_BTN_CODEX);

		CHA_CLAIM = scaledImageLocal(STR_CHA_CLAIM);
		CHA_CLAIM_SMALL = scaledImageLocal(STR_CHA_CLAIM_SMALL);
		CHA_RED_DOT = scaledImageLocal(STR_CHA_RED_DOT);
		CHA_OK = scaledImageLocal(STR_CHA_OK);

		CHA_CODEX_LISTALL = scaledImageLocal(STR_CHA_CODEX_LISTALL);
		CHA_CODEX_OK = scaledImageLocal(STR_CHA_CODEX_OK);
		CHA_CODEX_REG_OK = scaledImageLocal(STR_CHA_CODEX_REG_OK);

		MAIL_RED_DOT_MAIN = scaledImageLocal(STR_MAIL_RED_DOT_MAIN);
		MAIL_RED_DOT = scaledImageLocal(STR_MAIL_RED_DOT);
		MAIL_COLLECT_ALL = scaledImageLocal(STR_MAIL_COLLECT_ALL);
		MAIL_SCREEN = scaledImageLocal(STR_MAIL_SCREEN);
		MAIL_NEXT = scaledImageLocal(STR_MAIL_NEXT);
		MAIL_OK = scaledImageLocal(STR_MAIL_OK);

		BTN_DUNGEON = scaledImageLocal(STR_BTN_DUNGEON);
		BTN_DUNGEON_NORMAL = scaledImageLocal(STR_BTN_DUNGEON_NORMAL);
		BTN_DUNGEON_ELITE = scaledImageLocal(STR_BTN_DUNGEON_ELITE);
		DUNGEON_ENTER = scaledImageLocal(STR_DUNGEON_ENTER);
		DUNGEON_ENTER_CONFIRM = scaledImageLocal(STR_DUNGEON_ENTER_CONFIRM);
		DUNGEON_ENTER_CONFIRM_CONFIRM = scaledImageLocal(STR_DUNGEON_ENTER_CONFIRM_CONFIRM);

		DUNGEON_PARTY_MANAGE = scaledImageLocal(STR_DUNGEON_PARTY_MANAGE);
		DUNGEON_PARTY_DISBAND = scaledImageLocal(STR_DUNGEON_PARTY_DISBAND);
		DUNGEON_PARTY_DISBAND_OK = scaledImageLocal(STR_DUNGEON_PARTY_DISBAND_OK);

		DUNGEON_PARTY_5PERCENT = scaledImageLocal(STR_DUNGEON_PARTY_5PERCENT);
		DUNGEON_PARTY_SEE = scaledImageLocal(STR_DUNGEON_PARTY_SEE);
		DUNGEON_PARTY_LEAVE = scaledImageLocal(STR_DUNGEON_PARTY_LEAVE);
		DUNGEON_PARTY_LEAVE_OK = scaledImageLocal(STR_DUNGEON_PARTY_LEAVE_OK);

		DUNGEON_PARTY_TAB = scaledImageLocal(STR_DUNGEON_PARTY_TAB);
		DUNGEON_PARTY_SEARCH = scaledImageLocal(STR_DUNGEON_PARTY_SEARCH);
		DUNGEON_PARTY_SEARCH_MANUAL = scaledImageLocal(STR_DUNGEON_PARTY_SEARCH_MANUAL);
		DUNGEON_PARTY_JOIN = scaledImageLocal(STR_DUNGEON_PARTY_JOIN);

		QUESTS_TAB = scaledImageLocal(STR_QUESTS_TAB);
		WORLD_MAP = scaledImageLocal(STR_WORLD_MAP);
		
		POTIONS_20 = scaledImageLocal(STR_POTIONS_20);
		POTIONS_15 = scaledImageLocal(STR_POTIONS_15);
		POTIONS_10 = scaledImageLocal(STR_POTIONS_10);
		POTIONS_0 = scaledImageLocal(STR_POTIONS_0);
		POTIONS_BUY = scaledImageLocal(STR_POTIONS_BUY);
		POTIONS_NO_ADENA = scaledImageLocal(STR_POTIONS_NO_ADENA);
		POTIONS_NO_ADENA_X = scaledImageLocal(STR_POTIONS_NO_ADENA_X);
		
		BTN_CONSUMABLES = scaledImageLocal(STR_CONSUMABLES);
		
		UPDATE_OK = scaledImageLocal(STR_UPDATE_OK);
		REWARD_X = scaledImageLocal(STR_REWARD_X);
		
		INVSELL_BULK = scaledImageLocal(STR_INVSELL_BULK);
		INVSELL_SELL = scaledImageLocal(STR_INVSELL_SELL);
		INVSELL_SELL_CONF = scaledImageLocal(STR_INVSELL_SELL_CONF);
		INVSELL_SELL_OK = scaledImageLocal(STR_INVSELL_SELL_OK);
		
	    setupDA_dungeon();
	    setupDR_dungeon();
		
	}
	
	static void setupDA_dungeon() {
		if (dungeonListDA.getSelectedIndex() == 0) DA_ELITE_DUNGEON_CURRENT_BTN = DA_ELITE_DUNGEON_CRUMA3; else 
		if (dungeonListDA.getSelectedIndex() == 1) DA_ELITE_DUNGEON_CURRENT_BTN = DA_ELITE_DUNGEON_IVORY1; else
		if (dungeonListDA.getSelectedIndex() == 2) DA_ELITE_DUNGEON_CURRENT_BTN = DA_ELITE_DUNGEON_IVORY2; else
		if (dungeonListDA.getSelectedIndex() == 3) DA_ELITE_DUNGEON_CURRENT_BTN = DA_ELITE_DUNGEON_IVORY3; else
		if (dungeonListDA.getSelectedIndex() == 4) DA_ELITE_DUNGEON_CURRENT_BTN = DA_ELITE_DUNGEON_FOS_CANOPY; else
		if (dungeonListDA.getSelectedIndex() == 5) DA_ELITE_DUNGEON_CURRENT_BTN = DA_ELITE_DUNGEON_FOS_UNDER; else
			DA_ELITE_DUNGEON_CURRENT_BTN = DA_ELITE_DUNGEON_CRUMA3;	
	}

	static void setupDR_dungeon() {
		
		//defaults
		DUNGEON_RUN_CURRENT_BTN = DA_ELITE_DUNGEON_CRUMA3;
		
		DUNGEON_WP1_X = Integer.parseInt(D1_WP1X.getText());
		DUNGEON_WP1_Y = Integer.parseInt(D1_WP1Y.getText());
		DUNGEON_WP1_D = Integer.parseInt(D1_WP1D.getText());
		DUNGEON_WP2_X = Integer.parseInt(D1_WP2X.getText());
		DUNGEON_WP2_Y = Integer.parseInt(D1_WP2Y.getText());
		DUNGEON_WP2_D = Integer.parseInt(D1_WP2D.getText());
		DUNGEON_WP3_X = Integer.parseInt(D1_WP3X.getText());
		DUNGEON_WP3_Y = Integer.parseInt(D1_WP3Y.getText());
		DUNGEON_WP3_D = Integer.parseInt(D1_WP3D.getText());
		DUNGEON_WP4_X = Integer.parseInt(D1_WP4X.getText());
		DUNGEON_WP4_Y = Integer.parseInt(D1_WP4Y.getText());
		DUNGEON_WP4_D = Integer.parseInt(D1_WP4D.getText());
		DUNGEON_WP5_X = Integer.parseInt(D1_WP5X.getText());
		DUNGEON_WP5_Y = Integer.parseInt(D1_WP5Y.getText());
		DUNGEON_WP5_D = Integer.parseInt(D1_WP5D.getText());
		
		DUNGEON_WP2_A = D1_WP2A.isSelected();
		DUNGEON_WP3_A = D1_WP3A.isSelected();
		DUNGEON_WP4_A = D1_WP4A.isSelected();
		DUNGEON_WP5_A = D1_WP5A.isSelected();

		
		if (dungeonListDR.getSelectedIndex() == 1) {
			DUNGEON_RUN_CURRENT_BTN = DA_ELITE_DUNGEON_IVORY1; 
			
			DUNGEON_WP1_X = Integer.parseInt(D2_WP1X.getText());
			DUNGEON_WP1_Y = Integer.parseInt(D2_WP1Y.getText());
			DUNGEON_WP1_D = Integer.parseInt(D2_WP1D.getText());
			DUNGEON_WP2_X = Integer.parseInt(D2_WP2X.getText());
			DUNGEON_WP2_Y = Integer.parseInt(D2_WP2Y.getText());
			DUNGEON_WP2_D = Integer.parseInt(D2_WP2D.getText());
			DUNGEON_WP3_X = Integer.parseInt(D2_WP3X.getText());
			DUNGEON_WP3_Y = Integer.parseInt(D2_WP3Y.getText());
			DUNGEON_WP3_D = Integer.parseInt(D2_WP3D.getText());
			DUNGEON_WP4_X = Integer.parseInt(D2_WP4X.getText());
			DUNGEON_WP4_Y = Integer.parseInt(D2_WP4Y.getText());
			DUNGEON_WP4_D = Integer.parseInt(D2_WP4D.getText());
			DUNGEON_WP5_X = Integer.parseInt(D2_WP5X.getText());
			DUNGEON_WP5_Y = Integer.parseInt(D2_WP5Y.getText());
			DUNGEON_WP5_D = Integer.parseInt(D2_WP5D.getText());
			
			DUNGEON_WP2_A = D2_WP2A.isSelected();
			DUNGEON_WP3_A = D2_WP3A.isSelected();
			DUNGEON_WP4_A = D2_WP4A.isSelected();
			DUNGEON_WP5_A = D2_WP5A.isSelected();

		}
		
		if (dungeonListDR.getSelectedIndex() == 2) {
			DUNGEON_RUN_CURRENT_BTN = DA_ELITE_DUNGEON_IVORY2; 
			
			DUNGEON_WP1_X = Integer.parseInt(D3_WP1X.getText());
			DUNGEON_WP1_Y = Integer.parseInt(D3_WP1Y.getText());
			DUNGEON_WP1_D = Integer.parseInt(D3_WP1D.getText());
			DUNGEON_WP2_X = Integer.parseInt(D3_WP2X.getText());
			DUNGEON_WP2_Y = Integer.parseInt(D3_WP2Y.getText());
			DUNGEON_WP2_D = Integer.parseInt(D3_WP2D.getText());
			DUNGEON_WP3_X = Integer.parseInt(D3_WP3X.getText());
			DUNGEON_WP3_Y = Integer.parseInt(D3_WP3Y.getText());
			DUNGEON_WP3_D = Integer.parseInt(D3_WP3D.getText());
			DUNGEON_WP4_X = Integer.parseInt(D3_WP4X.getText());
			DUNGEON_WP4_Y = Integer.parseInt(D3_WP4Y.getText());
			DUNGEON_WP4_D = Integer.parseInt(D3_WP4D.getText());
			DUNGEON_WP5_X = Integer.parseInt(D3_WP5X.getText());
			DUNGEON_WP5_Y = Integer.parseInt(D3_WP5Y.getText());
			DUNGEON_WP5_D = Integer.parseInt(D3_WP5D.getText());
			
			DUNGEON_WP2_A = D3_WP2A.isSelected();
			DUNGEON_WP3_A = D3_WP3A.isSelected();
			DUNGEON_WP4_A = D3_WP4A.isSelected();
			DUNGEON_WP5_A = D3_WP5A.isSelected();

		}
		
		if (dungeonListDR.getSelectedIndex() == 3) {
			DUNGEON_RUN_CURRENT_BTN = DA_ELITE_DUNGEON_IVORY3; 
			
			DUNGEON_WP1_X = Integer.parseInt(D4_WP1X.getText());
			DUNGEON_WP1_Y = Integer.parseInt(D4_WP1Y.getText());
			DUNGEON_WP1_D = Integer.parseInt(D4_WP1D.getText());
			DUNGEON_WP2_X = Integer.parseInt(D4_WP2X.getText());
			DUNGEON_WP2_Y = Integer.parseInt(D4_WP2Y.getText());
			DUNGEON_WP2_D = Integer.parseInt(D4_WP2D.getText());
			DUNGEON_WP3_X = Integer.parseInt(D4_WP3X.getText());
			DUNGEON_WP3_Y = Integer.parseInt(D4_WP3Y.getText());
			DUNGEON_WP3_D = Integer.parseInt(D4_WP3D.getText());
			DUNGEON_WP4_X = Integer.parseInt(D4_WP4X.getText());
			DUNGEON_WP4_Y = Integer.parseInt(D4_WP4Y.getText());
			DUNGEON_WP4_D = Integer.parseInt(D4_WP4D.getText());
			DUNGEON_WP5_X = Integer.parseInt(D4_WP5X.getText());
			DUNGEON_WP5_Y = Integer.parseInt(D4_WP5Y.getText());
			DUNGEON_WP5_D = Integer.parseInt(D4_WP5D.getText());
			
			DUNGEON_WP2_A = D4_WP2A.isSelected();
			DUNGEON_WP3_A = D4_WP3A.isSelected();
			DUNGEON_WP4_A = D4_WP4A.isSelected();
			DUNGEON_WP5_A = D4_WP5A.isSelected();

		}
		
		if (dungeonListDR.getSelectedIndex() == 4) {
			DUNGEON_RUN_CURRENT_BTN = DA_ELITE_DUNGEON_FOS_CANOPY; 
			
			DUNGEON_WP1_X = Integer.parseInt(D5_WP1X.getText());
			DUNGEON_WP1_Y = Integer.parseInt(D5_WP1Y.getText());
			DUNGEON_WP1_D = Integer.parseInt(D5_WP1D.getText());
			DUNGEON_WP2_X = Integer.parseInt(D5_WP2X.getText());
			DUNGEON_WP2_Y = Integer.parseInt(D5_WP2Y.getText());
			DUNGEON_WP2_D = Integer.parseInt(D5_WP2D.getText());
			DUNGEON_WP3_X = Integer.parseInt(D5_WP3X.getText());
			DUNGEON_WP3_Y = Integer.parseInt(D5_WP3Y.getText());
			DUNGEON_WP3_D = Integer.parseInt(D5_WP3D.getText());
			DUNGEON_WP4_X = Integer.parseInt(D5_WP4X.getText());
			DUNGEON_WP4_Y = Integer.parseInt(D5_WP4Y.getText());
			DUNGEON_WP4_D = Integer.parseInt(D5_WP4D.getText());
			DUNGEON_WP5_X = Integer.parseInt(D5_WP5X.getText());
			DUNGEON_WP5_Y = Integer.parseInt(D5_WP5Y.getText());
			DUNGEON_WP5_D = Integer.parseInt(D5_WP5D.getText());
			
			DUNGEON_WP2_A = D5_WP2A.isSelected();
			DUNGEON_WP3_A = D5_WP3A.isSelected();
			DUNGEON_WP4_A = D5_WP4A.isSelected();
			DUNGEON_WP5_A = D5_WP5A.isSelected();

		}
		
		if (dungeonListDR.getSelectedIndex() == 5) {
			DUNGEON_RUN_CURRENT_BTN = DA_ELITE_DUNGEON_FOS_UNDER; 
			
			DUNGEON_WP1_X = Integer.parseInt(D6_WP1X.getText());
			DUNGEON_WP1_Y = Integer.parseInt(D6_WP1Y.getText());
			DUNGEON_WP1_D = Integer.parseInt(D6_WP1D.getText());
			DUNGEON_WP2_X = Integer.parseInt(D6_WP2X.getText());
			DUNGEON_WP2_Y = Integer.parseInt(D6_WP2Y.getText());
			DUNGEON_WP2_D = Integer.parseInt(D6_WP2D.getText());
			DUNGEON_WP3_X = Integer.parseInt(D6_WP3X.getText());
			DUNGEON_WP3_Y = Integer.parseInt(D6_WP3Y.getText());
			DUNGEON_WP3_D = Integer.parseInt(D6_WP3D.getText());
			DUNGEON_WP4_X = Integer.parseInt(D6_WP4X.getText());
			DUNGEON_WP4_Y = Integer.parseInt(D6_WP4Y.getText());
			DUNGEON_WP4_D = Integer.parseInt(D6_WP4D.getText());
			DUNGEON_WP5_X = Integer.parseInt(D6_WP5X.getText());
			DUNGEON_WP5_Y = Integer.parseInt(D6_WP5Y.getText());
			DUNGEON_WP5_D = Integer.parseInt(D6_WP5D.getText());
			
			DUNGEON_WP2_A = D6_WP2A.isSelected();
			DUNGEON_WP3_A = D6_WP3A.isSelected();
			DUNGEON_WP4_A = D6_WP4A.isSelected();
			DUNGEON_WP5_A = D6_WP5A.isSelected();

		}
		
		DUNGEON_WPLAST_X = DUNGEON_WP1_X;
		DUNGEON_WPLAST_Y = DUNGEON_WP1_Y;
		if (DUNGEON_WP2_A) {
			DUNGEON_WPLAST_X = DUNGEON_WP2_X;
			DUNGEON_WPLAST_Y = DUNGEON_WP2_Y;
		}
		if (DUNGEON_WP3_A) {
			DUNGEON_WPLAST_X = DUNGEON_WP3_X;
			DUNGEON_WPLAST_Y = DUNGEON_WP3_Y;
		}
		if (DUNGEON_WP4_A) {
			DUNGEON_WPLAST_X = DUNGEON_WP4_X;
			DUNGEON_WPLAST_Y = DUNGEON_WP4_Y;
		}
		if (DUNGEON_WP5_A) {
			DUNGEON_WPLAST_X = DUNGEON_WP5_X;
			DUNGEON_WPLAST_Y = DUNGEON_WP5_Y;
		}
		
	}

	
	static void loadSettings() {
		File configFile = new File("l2rbot.config");
		 
		try {
		    FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    if (props.getProperty("DA_FREE_DRAW").equals("enabled")) cb_DA_FREE_DRAW.setSelected(true); else cb_DA_FREE_DRAW.setSelected(false);
		    if (props.getProperty("DA_TOI").equals("enabled")) cb_DA_TOI.setSelected(true); else cb_DA_TOI.setSelected(false);
		    if (props.getProperty("DA_FRIENDS").equals("enabled")) cb_DA_FRIENDS.setSelected(true); else cb_DA_FRIENDS.setSelected(false);
		    if (props.getProperty("DA_DD").equals("enabled")) cb_DA_DD.setSelected(true); else cb_DA_DD.setSelected(false);
		    if (props.getProperty("DA_CLAN_DONATION").equals("enabled")) cb_DA_CLAN_DONATION.setSelected(true); else cb_DA_CLAN_DONATION.setSelected(false);
		    if (props.getProperty("DA_CLAN_SHOP").equals("enabled")) cb_DA_CLAN_SHOP.setSelected(true); else cb_DA_CLAN_SHOP.setSelected(false);
		    if (props.getProperty("DA_CLAN_CHECKIN").equals("enabled")) cb_DA_CLAN_CHECKIN.setSelected(true); else cb_DA_CLAN_CHECKIN.setSelected(false);
		    if (props.getProperty("DA_CLAN_MEMBERS").equals("enabled")) cb_DA_CLAN_MEMBERS.setSelected(true); else cb_DA_CLAN_MEMBERS.setSelected(false);
		    if (props.getProperty("DA_ARENA").equals("enabled")) cb_DA_ARENA.setSelected(true); else cb_DA_ARENA.setSelected(false);
		    if (props.getProperty("DA_ELITE_DUNGEON").equals("enabled")) cb_DA_ELITE_DUNGEON.setSelected(true); else cb_DA_ELITE_DUNGEON.setSelected(false);
		    if (props.getProperty("DA_HERBS").equals("enabled")) cb_DA_HERBS.setSelected(true); else cb_DA_HERBS.setSelected(false);
		    if (props.getProperty("DA_FREE_HG").equals("enabled")) cb_DA_FREE_HG.setSelected(true); else cb_DA_FREE_HG.setSelected(false);
		    if (props.getProperty("DA_TEMPLE").equals("enabled")) cb_DA_TEMPLE.setSelected(true); else cb_DA_TEMPLE.setSelected(false);
		    if (props.getProperty("DA_VAULT").equals("enabled")) cb_DA_VAULT.setSelected(true); else cb_DA_VAULT.setSelected(false);
		    if (props.getProperty("DA_TRIALS").equals("enabled")) cb_DA_TRIALS.setSelected(true); else cb_DA_TRIALS.setSelected(false);
		    if (props.getProperty("DA_CIRCLE").equals("enabled")) cb_DA_CIRCLE.setSelected(true); else cb_DA_CIRCLE.setSelected(false);
		    
		    if (props.getProperty("DAILIES").equals("enabled")) cb_DAILIES.setSelected(true); else cb_DAILIES.setSelected(false);
		    if (props.getProperty("WEEKLY").equals("enabled")) cb_WEEKLY.setSelected(true); else cb_WEEKLY.setSelected(false);
		    if (props.getProperty("SUBQUEST").equals("enabled")) cb_SUBQUEST.setSelected(true); else cb_SUBQUEST.setSelected(false);
		    if (props.getProperty("MAIN_QUEST").equals("enabled")) cb_MAIN_QUEST.setSelected(true); else cb_MAIN_QUEST.setSelected(false);
		    if (props.getProperty("CH_STUFF").equals("enabled")) cb_CH_STUFF.setSelected(true); else cb_CH_STUFF.setSelected(false);
		    if (props.getProperty("CHALLENGES").equals("enabled")) cb_CHALLENGES.setSelected(true); else cb_CHALLENGES.setSelected(false);
		    if (props.getProperty("READ_MAIL").equals("enabled")) cb_SORT_MAIL.setSelected(true); else cb_SORT_MAIL.setSelected(false);
		    if (props.getProperty("INV_SORT").equals("enabled")) cb_INV_SORT.setSelected(true); else cb_INV_SORT.setSelected(false);
		    if (props.getProperty("DUNGEON_RUN").equals("enabled")) cb_DUNGEON_RUN.setSelected(true); else cb_DUNGEON_RUN.setSelected(false);
		    if (props.getProperty("DELAYED_RUN").equals("enabled")) cb_DELAYED_RUN.setSelected(true); else cb_DELAYED_RUN.setSelected(false);
		    
		    tf_DELAYED_RUN.setText(props.getProperty("DELAYED_RUN_TIME"));
		    
		    if (props.getProperty("SUBQUEST_RESET").equals("enabled")) cb_SUBQUEST_RESET.setSelected(true); else cb_SUBQUEST_RESET.setSelected(false);
		    if (props.getProperty("SUBQUEST_BUY_SCROLL_A").equals("enabled")) cb_SUBQUEST_BUY_SCROLL_A.setSelected(true); else cb_SUBQUEST_BUY_SCROLL_A.setSelected(false);
		    
		    if (props.getProperty("HERBS_EINHASADS").equals("enabled")) cb_HERBS_EINHASADS.setSelected(true); else cb_HERBS_EINHASADS.setSelected(false);
		    
		    //SETTINGS

		    emulatorList.setSelectedIndex(Integer.parseInt(props.getProperty("EMULATOR_LIST")));
		    resolutionList.setSelectedIndex(Integer.parseInt(props.getProperty("RESOLUTION_LIST")));
		    
		    setupScreen.setText(props.getProperty("SETUP_SCREEN"));
		    setupScale.setText(props.getProperty("SETUP_SCALE"));

		    setupOffsetX.setText(props.getProperty("SETUP_OFFSET_X"));
		    setupOffsetY.setText(props.getProperty("SETUP_OFFSET_Y"));

		    coordHerbs1X.setText(props.getProperty("GATHER_ON_MAP1_X"));
		    coordHerbs1Y.setText(props.getProperty("GATHER_ON_MAP1_Y"));
		    coordHerbs2X.setText(props.getProperty("GATHER_ON_MAP2_X"));
		    coordHerbs2Y.setText(props.getProperty("GATHER_ON_MAP2_Y"));
		    coordHerbs3X.setText(props.getProperty("GATHER_ON_MAP3_X"));
		    coordHerbs3Y.setText(props.getProperty("GATHER_ON_MAP3_Y"));
		    
		    dungeonListDA.setSelectedIndex(Integer.parseInt(props.getProperty("DUNGEON_LIST_DA")));
		    dungeonListDR.setSelectedIndex(Integer.parseInt(props.getProperty("DUNGEON_LIST_DR")));
		    
		    D1_WP1X.setText(props.getProperty("D1_WP1X"));
		    D1_WP1Y.setText(props.getProperty("D1_WP1Y"));
		    D1_WP1D.setText(props.getProperty("D1_WP1D"));
		    D1_WP2X.setText(props.getProperty("D1_WP2X"));
		    D1_WP2Y.setText(props.getProperty("D1_WP2Y"));
		    D1_WP2D.setText(props.getProperty("D1_WP2D"));
		    if (props.getProperty("D1_WP2A").equals("enabled")) D1_WP2A.setSelected(true); else D1_WP2A.setSelected(false);
		    D1_WP3X.setText(props.getProperty("D1_WP3X"));
		    D1_WP3Y.setText(props.getProperty("D1_WP3Y"));
		    D1_WP3D.setText(props.getProperty("D1_WP3D"));
		    if (props.getProperty("D1_WP3A").equals("enabled")) D1_WP3A.setSelected(true); else D1_WP3A.setSelected(false);
		    D1_WP4X.setText(props.getProperty("D1_WP4X"));
		    D1_WP4Y.setText(props.getProperty("D1_WP4Y"));
		    D1_WP4D.setText(props.getProperty("D1_WP4D"));
		    if (props.getProperty("D1_WP4A").equals("enabled")) D1_WP4A.setSelected(true); else D1_WP4A.setSelected(false);
		    D1_WP5X.setText(props.getProperty("D1_WP5X"));
		    D1_WP5Y.setText(props.getProperty("D1_WP5Y"));
		    D1_WP5D.setText(props.getProperty("D1_WP5D"));
		    if (props.getProperty("D1_WP5A").equals("enabled")) D1_WP5A.setSelected(true); else D1_WP5A.setSelected(false);

		    D2_WP1X.setText(props.getProperty("D2_WP1X"));
		    D2_WP1Y.setText(props.getProperty("D2_WP1Y"));
		    D2_WP1D.setText(props.getProperty("D2_WP1D"));
		    D2_WP2X.setText(props.getProperty("D2_WP2X"));
		    D2_WP2Y.setText(props.getProperty("D2_WP2Y"));
		    D2_WP2D.setText(props.getProperty("D2_WP2D"));
		    if (props.getProperty("D2_WP2A").equals("enabled")) D2_WP2A.setSelected(true); else D2_WP2A.setSelected(false);
		    D2_WP3X.setText(props.getProperty("D2_WP3X"));
		    D2_WP3Y.setText(props.getProperty("D2_WP3Y"));
		    D2_WP3D.setText(props.getProperty("D2_WP3D"));
		    if (props.getProperty("D2_WP3A").equals("enabled")) D2_WP3A.setSelected(true); else D2_WP3A.setSelected(false);
		    D2_WP4X.setText(props.getProperty("D2_WP4X"));
		    D2_WP4Y.setText(props.getProperty("D2_WP4Y"));
		    D2_WP4D.setText(props.getProperty("D2_WP4D"));
		    if (props.getProperty("D2_WP4A").equals("enabled")) D2_WP4A.setSelected(true); else D2_WP4A.setSelected(false);
		    D2_WP5X.setText(props.getProperty("D2_WP5X"));
		    D2_WP5Y.setText(props.getProperty("D2_WP5Y"));
		    D2_WP5D.setText(props.getProperty("D2_WP5D"));
		    if (props.getProperty("D2_WP5A").equals("enabled")) D2_WP5A.setSelected(true); else D2_WP5A.setSelected(false);

		    D3_WP1X.setText(props.getProperty("D3_WP1X"));
		    D3_WP1Y.setText(props.getProperty("D3_WP1Y"));
		    D3_WP1D.setText(props.getProperty("D3_WP1D"));
		    D3_WP2X.setText(props.getProperty("D3_WP2X"));
		    D3_WP2Y.setText(props.getProperty("D3_WP2Y"));
		    D3_WP2D.setText(props.getProperty("D3_WP2D"));
		    if (props.getProperty("D3_WP2A").equals("enabled")) D3_WP2A.setSelected(true); else D3_WP2A.setSelected(false);
		    D3_WP3X.setText(props.getProperty("D3_WP3X"));
		    D3_WP3Y.setText(props.getProperty("D3_WP3Y"));
		    D3_WP3D.setText(props.getProperty("D3_WP3D"));
		    if (props.getProperty("D3_WP3A").equals("enabled")) D3_WP3A.setSelected(true); else D3_WP3A.setSelected(false);
		    D3_WP4X.setText(props.getProperty("D3_WP4X"));
		    D3_WP4Y.setText(props.getProperty("D3_WP4Y"));
		    D3_WP4D.setText(props.getProperty("D3_WP4D"));
		    if (props.getProperty("D3_WP4A").equals("enabled")) D3_WP4A.setSelected(true); else D3_WP4A.setSelected(false);
		    D3_WP5X.setText(props.getProperty("D3_WP5X"));
		    D3_WP5Y.setText(props.getProperty("D3_WP5Y"));
		    D3_WP5D.setText(props.getProperty("D3_WP5D"));
		    if (props.getProperty("D3_WP5A").equals("enabled")) D3_WP5A.setSelected(true); else D3_WP5A.setSelected(false);

		    D4_WP1X.setText(props.getProperty("D4_WP1X"));
		    D4_WP1Y.setText(props.getProperty("D4_WP1Y"));
		    D4_WP1D.setText(props.getProperty("D4_WP1D"));
		    D4_WP2X.setText(props.getProperty("D4_WP2X"));
		    D4_WP2Y.setText(props.getProperty("D4_WP2Y"));
		    D4_WP2D.setText(props.getProperty("D4_WP2D"));
		    if (props.getProperty("D4_WP2A").equals("enabled")) D4_WP2A.setSelected(true); else D4_WP2A.setSelected(false);
		    D4_WP3X.setText(props.getProperty("D4_WP3X"));
		    D4_WP3Y.setText(props.getProperty("D4_WP3Y"));
		    D4_WP3D.setText(props.getProperty("D4_WP3D"));
		    if (props.getProperty("D4_WP3A").equals("enabled")) D4_WP3A.setSelected(true); else D4_WP3A.setSelected(false);
		    D4_WP4X.setText(props.getProperty("D4_WP4X"));
		    D4_WP4Y.setText(props.getProperty("D4_WP4Y"));
		    D4_WP4D.setText(props.getProperty("D4_WP4D"));
		    if (props.getProperty("D4_WP4A").equals("enabled")) D4_WP4A.setSelected(true); else D4_WP4A.setSelected(false);
		    D4_WP5X.setText(props.getProperty("D4_WP5X"));
		    D4_WP5Y.setText(props.getProperty("D4_WP5Y"));
		    D4_WP5D.setText(props.getProperty("D4_WP5D"));
		    if (props.getProperty("D4_WP5A").equals("enabled")) D4_WP5A.setSelected(true); else D4_WP5A.setSelected(false);

		    D5_WP1X.setText(props.getProperty("D5_WP1X"));
		    D5_WP1Y.setText(props.getProperty("D5_WP1Y"));
		    D5_WP1D.setText(props.getProperty("D5_WP1D"));
		    D5_WP2X.setText(props.getProperty("D5_WP2X"));
		    D5_WP2Y.setText(props.getProperty("D5_WP2Y"));
		    D5_WP2D.setText(props.getProperty("D5_WP2D"));
		    if (props.getProperty("D5_WP2A").equals("enabled")) D5_WP2A.setSelected(true); else D5_WP2A.setSelected(false);
		    D5_WP3X.setText(props.getProperty("D5_WP3X"));
		    D5_WP3Y.setText(props.getProperty("D5_WP3Y"));
		    D5_WP3D.setText(props.getProperty("D5_WP3D"));
		    if (props.getProperty("D5_WP3A").equals("enabled")) D5_WP3A.setSelected(true); else D5_WP3A.setSelected(false);
		    D5_WP4X.setText(props.getProperty("D5_WP4X"));
		    D5_WP4Y.setText(props.getProperty("D5_WP4Y"));
		    D5_WP4D.setText(props.getProperty("D5_WP4D"));
		    if (props.getProperty("D5_WP4A").equals("enabled")) D5_WP4A.setSelected(true); else D5_WP4A.setSelected(false);
		    D5_WP5X.setText(props.getProperty("D5_WP5X"));
		    D5_WP5Y.setText(props.getProperty("D5_WP5Y"));
		    D5_WP5D.setText(props.getProperty("D5_WP5D"));
		    if (props.getProperty("D5_WP5A").equals("enabled")) D5_WP5A.setSelected(true); else D5_WP5A.setSelected(false);

		    D6_WP1X.setText(props.getProperty("D6_WP1X"));
		    D6_WP1Y.setText(props.getProperty("D6_WP1Y"));
		    D6_WP1D.setText(props.getProperty("D6_WP1D"));
		    D6_WP2X.setText(props.getProperty("D6_WP2X"));
		    D6_WP2Y.setText(props.getProperty("D6_WP2Y"));
		    D6_WP2D.setText(props.getProperty("D6_WP2D"));
		    if (props.getProperty("D6_WP2A").equals("enabled")) D6_WP2A.setSelected(true); else D6_WP2A.setSelected(false);
		    D6_WP3X.setText(props.getProperty("D6_WP3X"));
		    D6_WP3Y.setText(props.getProperty("D6_WP3Y"));
		    D6_WP3D.setText(props.getProperty("D6_WP3D"));
		    if (props.getProperty("D6_WP3A").equals("enabled")) D6_WP3A.setSelected(true); else D6_WP3A.setSelected(false);
		    D6_WP4X.setText(props.getProperty("D6_WP4X"));
		    D6_WP4Y.setText(props.getProperty("D6_WP4Y"));
		    D6_WP4D.setText(props.getProperty("D6_WP4D"));
		    if (props.getProperty("D6_WP4A").equals("enabled")) D6_WP4A.setSelected(true); else D6_WP4A.setSelected(false);
		    D6_WP5X.setText(props.getProperty("D6_WP5X"));
		    D6_WP5Y.setText(props.getProperty("D6_WP5Y"));
		    D6_WP5D.setText(props.getProperty("D6_WP5D"));
		    if (props.getProperty("D6_WP5A").equals("enabled")) D6_WP5A.setSelected(true); else D6_WP5A.setSelected(false);

		    setupSleep.setText(props.getProperty("SETUP_SLEEP"));
		    
		    log("Init - settings loaded..");
		    
		    reader.close();
		} catch (FileNotFoundException | NullPointerException ex) {
		    // file does not exist - defaults to defaults
			// ex.printStackTrace();
			
			log("Init - no settings file found .. loading defaults");
			
			cb_DA_FREE_DRAW.setSelected(true);
			cb_DA_TOI.setSelected(true);
			cb_DA_FRIENDS.setSelected(true);
			cb_DA_DD.setSelected(true);
			cb_DA_CLAN_DONATION.setSelected(true);
			cb_DA_CLAN_SHOP.setSelected(true);
			cb_DA_CLAN_CHECKIN.setSelected(true);
			cb_DA_CLAN_MEMBERS.setSelected(true);
			cb_DA_ARENA.setSelected(false);
			cb_DA_ELITE_DUNGEON.setSelected(false);
			cb_DA_HERBS.setSelected(false);
			cb_DA_FREE_HG.setSelected(false);
			cb_DA_TEMPLE.setSelected(true);
			cb_DA_VAULT.setSelected(true);
			cb_DA_TRIALS.setSelected(true);
			cb_DA_CIRCLE.setSelected(true);
			
			cb_DAILIES.setSelected(true);
			cb_WEEKLY.setSelected(true);
			cb_SUBQUEST.setSelected(true);
			cb_MAIN_QUEST.setSelected(true);
			cb_CH_STUFF.setSelected(true);
			cb_CHALLENGES.setSelected(true);
			cb_SORT_MAIL.setSelected(false);
			cb_INV_SORT.setSelected(false);
			cb_DUNGEON_RUN.setSelected(true);
			cb_DELAYED_RUN.setSelected(true);
			tf_DELAYED_RUN.setText("10:05");
			
			cb_SUBQUEST_RESET.setSelected(false);
			cb_SUBQUEST_BUY_SCROLL_A.setSelected(false);

			cb_HERBS_EINHASADS.setSelected(false);
			
			emulatorList.setSelectedIndex(0);
			resolutionList.setSelectedIndex(0);
			
			setupScreen.setText(Integer.toString(DEF_SCREEN));
			setupScale.setText(Float.toString(DEF_SCALE));
			
		    setupOffsetX.setText(Integer.toString(DEF_OFFSETX));
		    setupOffsetY.setText(Integer.toString(DEF_OFFSETY));

			coordHerbs1X.setText(Integer.toString(DEF_GATHER_ON_MAP1_X));
			coordHerbs1Y.setText(Integer.toString(DEF_GATHER_ON_MAP1_Y));
			coordHerbs2X.setText(Integer.toString(DEF_GATHER_ON_MAP2_X));
			coordHerbs2Y.setText(Integer.toString(DEF_GATHER_ON_MAP2_Y));
			coordHerbs3X.setText(Integer.toString(DEF_GATHER_ON_MAP3_X));
			coordHerbs3Y.setText(Integer.toString(DEF_GATHER_ON_MAP3_Y));
			
			dungeonListDA.setSelectedIndex(DEF_DA_DUNGEON);
			dungeonListDR.setSelectedIndex(DEF_DR_DUNGEON);
			
		    D1_WP1X.setText(Integer.toString(DEF_D1_WP1_X));
		    D1_WP1Y.setText(Integer.toString(DEF_D1_WP1_Y));
		    D1_WP1D.setText(Integer.toString(DEF_D1_WP1_D));
		    D1_WP2X.setText(Integer.toString(DEF_D1_WP2_X));
		    D1_WP2Y.setText(Integer.toString(DEF_D1_WP2_Y));
		    D1_WP2D.setText(Integer.toString(DEF_D1_WP2_D));
		    D1_WP2A.setSelected(DEF_D1_WP2_A);
		    D1_WP3X.setText(Integer.toString(DEF_D1_WP3_X));
		    D1_WP3Y.setText(Integer.toString(DEF_D1_WP3_Y));
		    D1_WP3D.setText(Integer.toString(DEF_D1_WP3_D));
		    D1_WP3A.setSelected(DEF_D1_WP3_A);
		    D1_WP4X.setText(Integer.toString(DEF_D1_WP4_X));
		    D1_WP4Y.setText(Integer.toString(DEF_D1_WP4_Y));
		    D1_WP4D.setText(Integer.toString(DEF_D1_WP4_D));
		    D1_WP4A.setSelected(DEF_D1_WP4_A);
		    D1_WP5X.setText(Integer.toString(DEF_D1_WP5_X));
		    D1_WP5Y.setText(Integer.toString(DEF_D1_WP5_Y));
		    D1_WP5D.setText(Integer.toString(DEF_D1_WP5_D));
		    D1_WP5A.setSelected(DEF_D1_WP5_A);

		    D2_WP1X.setText(Integer.toString(DEF_D2_WP1_X));
		    D2_WP1Y.setText(Integer.toString(DEF_D2_WP1_Y));
		    D2_WP1D.setText(Integer.toString(DEF_D2_WP1_D));
		    D2_WP2X.setText(Integer.toString(DEF_D2_WP2_X));
		    D2_WP2Y.setText(Integer.toString(DEF_D2_WP2_Y));
		    D2_WP2D.setText(Integer.toString(DEF_D2_WP2_D));
		    D2_WP2A.setSelected(DEF_D2_WP2_A);
		    D2_WP3X.setText(Integer.toString(DEF_D2_WP3_X));
		    D2_WP3Y.setText(Integer.toString(DEF_D2_WP3_Y));
		    D2_WP3D.setText(Integer.toString(DEF_D2_WP3_D));
		    D2_WP3A.setSelected(DEF_D2_WP3_A);
		    D2_WP4X.setText(Integer.toString(DEF_D2_WP4_X));
		    D2_WP4Y.setText(Integer.toString(DEF_D2_WP4_Y));
		    D2_WP4D.setText(Integer.toString(DEF_D2_WP4_D));
		    D2_WP4A.setSelected(DEF_D2_WP4_A);
		    D2_WP5X.setText(Integer.toString(DEF_D2_WP5_X));
		    D2_WP5Y.setText(Integer.toString(DEF_D2_WP5_Y));
		    D2_WP5D.setText(Integer.toString(DEF_D2_WP5_D));
		    D2_WP5A.setSelected(DEF_D2_WP5_A);
			
		    D3_WP1X.setText(Integer.toString(DEF_D3_WP1_X));
		    D3_WP1Y.setText(Integer.toString(DEF_D3_WP1_Y));
		    D3_WP1D.setText(Integer.toString(DEF_D3_WP1_D));
		    D3_WP2X.setText(Integer.toString(DEF_D3_WP2_X));
		    D3_WP2Y.setText(Integer.toString(DEF_D3_WP2_Y));
		    D3_WP2D.setText(Integer.toString(DEF_D3_WP2_D));
		    D3_WP2A.setSelected(DEF_D3_WP2_A);
		    D3_WP3X.setText(Integer.toString(DEF_D3_WP3_X));
		    D3_WP3Y.setText(Integer.toString(DEF_D3_WP3_Y));
		    D3_WP3D.setText(Integer.toString(DEF_D3_WP3_D));
		    D3_WP3A.setSelected(DEF_D3_WP3_A);
		    D3_WP4X.setText(Integer.toString(DEF_D3_WP4_X));
		    D3_WP4Y.setText(Integer.toString(DEF_D3_WP4_Y));
		    D3_WP4D.setText(Integer.toString(DEF_D3_WP4_D));
		    D3_WP4A.setSelected(DEF_D3_WP4_A);
		    D3_WP5X.setText(Integer.toString(DEF_D3_WP5_X));
		    D3_WP5Y.setText(Integer.toString(DEF_D3_WP5_Y));
		    D3_WP5D.setText(Integer.toString(DEF_D3_WP5_D));
		    D3_WP5A.setSelected(DEF_D3_WP5_A);

		    D4_WP1X.setText(Integer.toString(DEF_D4_WP1_X));
		    D4_WP1Y.setText(Integer.toString(DEF_D4_WP1_Y));
		    D4_WP1D.setText(Integer.toString(DEF_D4_WP1_D));
		    D4_WP2X.setText(Integer.toString(DEF_D4_WP2_X));
		    D4_WP2Y.setText(Integer.toString(DEF_D4_WP2_Y));
		    D4_WP2D.setText(Integer.toString(DEF_D4_WP2_D));
		    D4_WP2A.setSelected(DEF_D4_WP2_A);
		    D4_WP3X.setText(Integer.toString(DEF_D4_WP3_X));
		    D4_WP3Y.setText(Integer.toString(DEF_D4_WP3_Y));
		    D4_WP3D.setText(Integer.toString(DEF_D4_WP3_D));
		    D4_WP3A.setSelected(DEF_D4_WP3_A);
		    D4_WP4X.setText(Integer.toString(DEF_D4_WP4_X));
		    D4_WP4Y.setText(Integer.toString(DEF_D4_WP4_Y));
		    D4_WP4D.setText(Integer.toString(DEF_D4_WP4_D));
		    D4_WP4A.setSelected(DEF_D4_WP4_A);
		    D4_WP5X.setText(Integer.toString(DEF_D4_WP5_X));
		    D4_WP5Y.setText(Integer.toString(DEF_D4_WP5_Y));
		    D4_WP5D.setText(Integer.toString(DEF_D4_WP5_D));
		    D4_WP5A.setSelected(DEF_D4_WP5_A);

		    D5_WP1X.setText(Integer.toString(DEF_D5_WP1_X));
		    D5_WP1Y.setText(Integer.toString(DEF_D5_WP1_Y));
		    D5_WP1D.setText(Integer.toString(DEF_D5_WP1_D));
		    D5_WP2X.setText(Integer.toString(DEF_D5_WP2_X));
		    D5_WP2Y.setText(Integer.toString(DEF_D5_WP2_Y));
		    D5_WP2D.setText(Integer.toString(DEF_D5_WP2_D));
		    D5_WP2A.setSelected(DEF_D5_WP2_A);
		    D5_WP3X.setText(Integer.toString(DEF_D5_WP3_X));
		    D5_WP3Y.setText(Integer.toString(DEF_D5_WP3_Y));
		    D5_WP3D.setText(Integer.toString(DEF_D5_WP3_D));
		    D5_WP3A.setSelected(DEF_D5_WP3_A);
		    D5_WP4X.setText(Integer.toString(DEF_D5_WP4_X));
		    D5_WP4Y.setText(Integer.toString(DEF_D5_WP4_Y));
		    D5_WP4D.setText(Integer.toString(DEF_D5_WP4_D));
		    D5_WP4A.setSelected(DEF_D5_WP4_A);
		    D5_WP5X.setText(Integer.toString(DEF_D5_WP5_X));
		    D5_WP5Y.setText(Integer.toString(DEF_D5_WP5_Y));
		    D5_WP5D.setText(Integer.toString(DEF_D5_WP5_D));
		    D5_WP5A.setSelected(DEF_D5_WP5_A);

		    D6_WP1X.setText(Integer.toString(DEF_D6_WP1_X));
		    D6_WP1Y.setText(Integer.toString(DEF_D6_WP1_Y));
		    D6_WP1D.setText(Integer.toString(DEF_D6_WP1_D));
		    D6_WP2X.setText(Integer.toString(DEF_D6_WP2_X));
		    D6_WP2Y.setText(Integer.toString(DEF_D6_WP2_Y));
		    D6_WP2D.setText(Integer.toString(DEF_D6_WP2_D));
		    D6_WP2A.setSelected(DEF_D6_WP2_A);
		    D6_WP3X.setText(Integer.toString(DEF_D6_WP3_X));
		    D6_WP3Y.setText(Integer.toString(DEF_D6_WP3_Y));
		    D6_WP3D.setText(Integer.toString(DEF_D6_WP3_D));
		    D6_WP3A.setSelected(DEF_D6_WP3_A);
		    D6_WP4X.setText(Integer.toString(DEF_D6_WP4_X));
		    D6_WP4Y.setText(Integer.toString(DEF_D6_WP4_Y));
		    D6_WP4D.setText(Integer.toString(DEF_D6_WP4_D));
		    D6_WP4A.setSelected(DEF_D6_WP4_A);
		    D6_WP5X.setText(Integer.toString(DEF_D6_WP5_X));
		    D6_WP5Y.setText(Integer.toString(DEF_D6_WP5_Y));
		    D6_WP5D.setText(Integer.toString(DEF_D6_WP5_D));
		    D6_WP5A.setSelected(DEF_D6_WP5_A);
		    
		    setupSleep.setText(Double.toString(DEF_SLEEP));
			
		} catch (IOException ex) {
		    // I/O error
		}
	}
	
	static void saveSettings() {
		File configFile = new File("l2rbot.config");
		 
		try {
		    Properties props = new Properties();
		    
		    if (cb_DA_FREE_DRAW.isSelected()) props.setProperty("DA_FREE_DRAW", "enabled"); else props.setProperty("DA_FREE_DRAW", "disabled");
		    if (cb_DA_TOI.isSelected()) props.setProperty("DA_TOI", "enabled"); else props.setProperty("DA_TOI", "disabled");
		    if (cb_DA_FRIENDS.isSelected()) props.setProperty("DA_FRIENDS", "enabled"); else props.setProperty("DA_FRIENDS", "disabled");
		    if (cb_DA_DD.isSelected()) props.setProperty("DA_DD", "enabled"); else props.setProperty("DA_DD", "disabled");
		    if (cb_DA_CLAN_DONATION.isSelected()) props.setProperty("DA_CLAN_DONATION", "enabled"); else props.setProperty("DA_CLAN_DONATION", "disabled");
		    if (cb_DA_CLAN_SHOP.isSelected()) props.setProperty("DA_CLAN_SHOP", "enabled"); else props.setProperty("DA_CLAN_SHOP", "disabled");
		    if (cb_DA_CLAN_CHECKIN.isSelected()) props.setProperty("DA_CLAN_CHECKIN", "enabled"); else props.setProperty("DA_CLAN_CHECKIN", "disabled");
		    if (cb_DA_CLAN_MEMBERS.isSelected()) props.setProperty("DA_CLAN_MEMBERS", "enabled"); else props.setProperty("DA_CLAN_MEMBERS", "disabled");
		    if (cb_DA_ARENA.isSelected()) props.setProperty("DA_ARENA", "enabled"); else props.setProperty("DA_ARENA", "disabled");
		    if (cb_DA_ELITE_DUNGEON.isSelected()) props.setProperty("DA_ELITE_DUNGEON", "enabled"); else props.setProperty("DA_ELITE_DUNGEON", "disabled");
		    if (cb_DA_HERBS.isSelected()) props.setProperty("DA_HERBS", "enabled"); else props.setProperty("DA_HERBS", "disabled");
		    if (cb_DA_FREE_HG.isSelected()) props.setProperty("DA_FREE_HG", "enabled"); else props.setProperty("DA_FREE_HG", "disabled");
		    if (cb_DA_TEMPLE.isSelected()) props.setProperty("DA_TEMPLE", "enabled"); else props.setProperty("DA_TEMPLE", "disabled");
		    if (cb_DA_VAULT.isSelected()) props.setProperty("DA_VAULT", "enabled"); else props.setProperty("DA_VAULT", "disabled");
		    if (cb_DA_TRIALS.isSelected()) props.setProperty("DA_TRIALS", "enabled"); else props.setProperty("DA_TRIALS", "disabled");
		    if (cb_DA_CIRCLE.isSelected()) props.setProperty("DA_CIRCLE", "enabled"); else props.setProperty("DA_CIRCLE", "disabled");
  		    
		    if (cb_DAILIES.isSelected()) props.setProperty("DAILIES", "enabled"); else props.setProperty("DAILIES", "disabled");
		    if (cb_WEEKLY.isSelected()) props.setProperty("WEEKLY", "enabled"); else props.setProperty("WEEKLY", "disabled");
		    if (cb_SUBQUEST.isSelected()) props.setProperty("SUBQUEST", "enabled"); else props.setProperty("SUBQUEST", "disabled");
		    if (cb_MAIN_QUEST.isSelected()) props.setProperty("MAIN_QUEST", "enabled"); else props.setProperty("MAIN_QUEST", "disabled");
		    if (cb_CH_STUFF.isSelected()) props.setProperty("CH_STUFF", "enabled"); else props.setProperty("CH_STUFF", "disabled");
		    if (cb_CHALLENGES.isSelected()) props.setProperty("CHALLENGES", "enabled"); else props.setProperty("CHALLENGES", "disabled");
		    if (cb_SORT_MAIL.isSelected()) props.setProperty("READ_MAIL", "enabled"); else props.setProperty("READ_MAIL", "disabled");
		    if (cb_INV_SORT.isSelected()) props.setProperty("INV_SORT", "enabled"); else props.setProperty("INV_SORT", "disabled");
		    if (cb_DUNGEON_RUN.isSelected()) props.setProperty("DUNGEON_RUN", "enabled"); else props.setProperty("DUNGEON_RUN", "disabled");
		    if (cb_DELAYED_RUN.isSelected()) props.setProperty("DELAYED_RUN", "enabled"); else props.setProperty("DELAYED_RUN", "disabled");

		    props.setProperty("DELAYED_RUN_TIME", tf_DELAYED_RUN.getText());
		    
		    if (cb_SUBQUEST_RESET.isSelected()) props.setProperty("SUBQUEST_RESET", "enabled"); else props.setProperty("SUBQUEST_RESET", "disabled");
		    if (cb_SUBQUEST_BUY_SCROLL_A.isSelected()) props.setProperty("SUBQUEST_BUY_SCROLL_A", "enabled"); else props.setProperty("SUBQUEST_BUY_SCROLL_A", "disabled");

		    if (cb_HERBS_EINHASADS.isSelected()) props.setProperty("HERBS_EINHASADS", "enabled"); else props.setProperty("HERBS_EINHASADS", "disabled");
		    
		    props.setProperty("EMULATOR_LIST", Integer.toString(emulatorList.getSelectedIndex()));
		    props.setProperty("RESOLUTION_LIST", Integer.toString(resolutionList.getSelectedIndex()));
		    
		    props.setProperty("SETUP_SCREEN", setupScreen.getText());
		    props.setProperty("SETUP_SCALE", setupScale.getText());
		    
		    props.setProperty("SETUP_OFFSET_X", setupOffsetX.getText());
		    props.setProperty("SETUP_OFFSET_Y", setupOffsetY.getText());
		    
		    props.setProperty("GATHER_ON_MAP1_X", coordHerbs1X.getText());
		    props.setProperty("GATHER_ON_MAP1_Y", coordHerbs1Y.getText());
		    props.setProperty("GATHER_ON_MAP2_X", coordHerbs2X.getText());
		    props.setProperty("GATHER_ON_MAP2_Y", coordHerbs2Y.getText());
		    props.setProperty("GATHER_ON_MAP3_X", coordHerbs3X.getText());
		    props.setProperty("GATHER_ON_MAP3_Y", coordHerbs3Y.getText());
		    
		    props.setProperty("DUNGEON_LIST_DA", Integer.toString(dungeonListDA.getSelectedIndex()));
		    props.setProperty("DUNGEON_LIST_DR", Integer.toString(dungeonListDR.getSelectedIndex()));
		    
		    props.setProperty("D1_WP1X", D1_WP1X.getText());
		    props.setProperty("D1_WP1Y", D1_WP1Y.getText());
		    props.setProperty("D1_WP1D", D1_WP1D.getText());
		    props.setProperty("D1_WP2X", D1_WP2X.getText());
		    props.setProperty("D1_WP2Y", D1_WP2Y.getText());
		    props.setProperty("D1_WP2D", D1_WP2D.getText());
		    if (D1_WP2A.isSelected()) props.setProperty("D1_WP2A", "enabled"); else props.setProperty("D1_WP2A", "disabled");
		    props.setProperty("D1_WP3X", D1_WP3X.getText());
		    props.setProperty("D1_WP3Y", D1_WP3Y.getText());
		    props.setProperty("D1_WP3D", D1_WP3D.getText());
		    if (D1_WP3A.isSelected()) props.setProperty("D1_WP3A", "enabled"); else props.setProperty("D1_WP3A", "disabled");
		    props.setProperty("D1_WP4X", D1_WP4X.getText());
		    props.setProperty("D1_WP4Y", D1_WP4Y.getText());
		    props.setProperty("D1_WP4D", D1_WP4D.getText());
		    if (D1_WP4A.isSelected()) props.setProperty("D1_WP4A", "enabled"); else props.setProperty("D1_WP4A", "disabled");
		    props.setProperty("D1_WP5X", D1_WP5X.getText());
		    props.setProperty("D1_WP5Y", D1_WP5Y.getText());
		    props.setProperty("D1_WP5D", D1_WP5D.getText());
		    if (D1_WP5A.isSelected()) props.setProperty("D1_WP5A", "enabled"); else props.setProperty("D1_WP5A", "disabled");

		    props.setProperty("D2_WP1X", D2_WP1X.getText());
		    props.setProperty("D2_WP1Y", D2_WP1Y.getText());
		    props.setProperty("D2_WP1D", D2_WP1D.getText());
		    props.setProperty("D2_WP2X", D2_WP2X.getText());
		    props.setProperty("D2_WP2Y", D2_WP2Y.getText());
		    props.setProperty("D2_WP2D", D2_WP2D.getText());
		    if (D2_WP2A.isSelected()) props.setProperty("D2_WP2A", "enabled"); else props.setProperty("D2_WP2A", "disabled");
		    props.setProperty("D2_WP3X", D2_WP3X.getText());
		    props.setProperty("D2_WP3Y", D2_WP3Y.getText());
		    props.setProperty("D2_WP3D", D2_WP3D.getText());
		    if (D2_WP3A.isSelected()) props.setProperty("D2_WP3A", "enabled"); else props.setProperty("D2_WP3A", "disabled");
		    props.setProperty("D2_WP4X", D2_WP4X.getText());
		    props.setProperty("D2_WP4Y", D2_WP4Y.getText());
		    props.setProperty("D2_WP4D", D2_WP4D.getText());
		    if (D2_WP4A.isSelected()) props.setProperty("D2_WP4A", "enabled"); else props.setProperty("D2_WP4A", "disabled");
		    props.setProperty("D2_WP5X", D2_WP5X.getText());
		    props.setProperty("D2_WP5Y", D2_WP5Y.getText());
		    props.setProperty("D2_WP5D", D2_WP5D.getText());
		    if (D2_WP5A.isSelected()) props.setProperty("D2_WP5A", "enabled"); else props.setProperty("D2_WP5A", "disabled");

		    props.setProperty("D3_WP1X", D3_WP1X.getText());
		    props.setProperty("D3_WP1Y", D3_WP1Y.getText());
		    props.setProperty("D3_WP1D", D3_WP1D.getText());
		    props.setProperty("D3_WP2X", D3_WP2X.getText());
		    props.setProperty("D3_WP2Y", D3_WP2Y.getText());
		    props.setProperty("D3_WP2D", D3_WP2D.getText());
		    if (D3_WP2A.isSelected()) props.setProperty("D3_WP2A", "enabled"); else props.setProperty("D3_WP2A", "disabled");
		    props.setProperty("D3_WP3X", D3_WP3X.getText());
		    props.setProperty("D3_WP3Y", D3_WP3Y.getText());
		    props.setProperty("D3_WP3D", D3_WP3D.getText());
		    if (D3_WP3A.isSelected()) props.setProperty("D3_WP3A", "enabled"); else props.setProperty("D3_WP3A", "disabled");
		    props.setProperty("D3_WP4X", D3_WP4X.getText());
		    props.setProperty("D3_WP4Y", D3_WP4Y.getText());
		    props.setProperty("D3_WP4D", D3_WP4D.getText());
		    if (D3_WP4A.isSelected()) props.setProperty("D3_WP4A", "enabled"); else props.setProperty("D3_WP4A", "disabled");
		    props.setProperty("D3_WP5X", D3_WP5X.getText());
		    props.setProperty("D3_WP5Y", D3_WP5Y.getText());
		    props.setProperty("D3_WP5D", D3_WP5D.getText());
		    if (D3_WP5A.isSelected()) props.setProperty("D3_WP5A", "enabled"); else props.setProperty("D3_WP5A", "disabled");

		    props.setProperty("D4_WP1X", D4_WP1X.getText());
		    props.setProperty("D4_WP1Y", D4_WP1Y.getText());
		    props.setProperty("D4_WP1D", D4_WP1D.getText());
		    props.setProperty("D4_WP2X", D4_WP2X.getText());
		    props.setProperty("D4_WP2Y", D4_WP2Y.getText());
		    props.setProperty("D4_WP2D", D4_WP2D.getText());
		    if (D4_WP2A.isSelected()) props.setProperty("D4_WP2A", "enabled"); else props.setProperty("D4_WP2A", "disabled");
		    props.setProperty("D4_WP3X", D4_WP3X.getText());
		    props.setProperty("D4_WP3Y", D4_WP3Y.getText());
		    props.setProperty("D4_WP3D", D4_WP3D.getText());
		    if (D4_WP3A.isSelected()) props.setProperty("D4_WP3A", "enabled"); else props.setProperty("D4_WP3A", "disabled");
		    props.setProperty("D4_WP4X", D4_WP4X.getText());
		    props.setProperty("D4_WP4Y", D4_WP4Y.getText());
		    props.setProperty("D4_WP4D", D4_WP4D.getText());
		    if (D4_WP4A.isSelected()) props.setProperty("D4_WP4A", "enabled"); else props.setProperty("D4_WP4A", "disabled");
		    props.setProperty("D4_WP5X", D4_WP5X.getText());
		    props.setProperty("D4_WP5Y", D4_WP5Y.getText());
		    props.setProperty("D4_WP5D", D4_WP5D.getText());
		    if (D4_WP5A.isSelected()) props.setProperty("D4_WP5A", "enabled"); else props.setProperty("D4_WP5A", "disabled");

		    props.setProperty("D5_WP1X", D5_WP1X.getText());
		    props.setProperty("D5_WP1Y", D5_WP1Y.getText());
		    props.setProperty("D5_WP1D", D5_WP1D.getText());
		    props.setProperty("D5_WP2X", D5_WP2X.getText());
		    props.setProperty("D5_WP2Y", D5_WP2Y.getText());
		    props.setProperty("D5_WP2D", D5_WP2D.getText());
		    if (D5_WP2A.isSelected()) props.setProperty("D5_WP2A", "enabled"); else props.setProperty("D5_WP2A", "disabled");
		    props.setProperty("D5_WP3X", D5_WP3X.getText());
		    props.setProperty("D5_WP3Y", D5_WP3Y.getText());
		    props.setProperty("D5_WP3D", D5_WP3D.getText());
		    if (D5_WP3A.isSelected()) props.setProperty("D5_WP3A", "enabled"); else props.setProperty("D5_WP3A", "disabled");
		    props.setProperty("D5_WP4X", D5_WP4X.getText());
		    props.setProperty("D5_WP4Y", D5_WP4Y.getText());
		    props.setProperty("D5_WP4D", D5_WP4D.getText());
		    if (D5_WP4A.isSelected()) props.setProperty("D5_WP4A", "enabled"); else props.setProperty("D5_WP4A", "disabled");
		    props.setProperty("D5_WP5X", D5_WP5X.getText());
		    props.setProperty("D5_WP5Y", D5_WP5Y.getText());
		    props.setProperty("D5_WP5D", D5_WP5D.getText());
		    if (D5_WP5A.isSelected()) props.setProperty("D5_WP5A", "enabled"); else props.setProperty("D5_WP5A", "disabled");

		    props.setProperty("D6_WP1X", D6_WP1X.getText());
		    props.setProperty("D6_WP1Y", D6_WP1Y.getText());
		    props.setProperty("D6_WP1D", D6_WP1D.getText());
		    props.setProperty("D6_WP2X", D6_WP2X.getText());
		    props.setProperty("D6_WP2Y", D6_WP2Y.getText());
		    props.setProperty("D6_WP2D", D6_WP2D.getText());
		    if (D6_WP2A.isSelected()) props.setProperty("D6_WP2A", "enabled"); else props.setProperty("D6_WP2A", "disabled");
		    props.setProperty("D6_WP3X", D6_WP3X.getText());
		    props.setProperty("D6_WP3Y", D6_WP3Y.getText());
		    props.setProperty("D6_WP3D", D6_WP3D.getText());
		    if (D6_WP3A.isSelected()) props.setProperty("D6_WP3A", "enabled"); else props.setProperty("D6_WP3A", "disabled");
		    props.setProperty("D6_WP4X", D6_WP4X.getText());
		    props.setProperty("D6_WP4Y", D6_WP4Y.getText());
		    props.setProperty("D6_WP4D", D6_WP4D.getText());
		    if (D6_WP4A.isSelected()) props.setProperty("D6_WP4A", "enabled"); else props.setProperty("D6_WP4A", "disabled");
		    props.setProperty("D6_WP5X", D6_WP5X.getText());
		    props.setProperty("D6_WP5Y", D6_WP5Y.getText());
		    props.setProperty("D6_WP5D", D6_WP5D.getText());
		    if (D6_WP5A.isSelected()) props.setProperty("D6_WP5A", "enabled"); else props.setProperty("D6_WP5A", "disabled");

		    props.setProperty("SETUP_SLEEP", setupSleep.getText());
		    
		    FileWriter writer = new FileWriter(configFile);
		    props.store(writer, "L2RBot settings");
		    writer.close();
		} catch (FileNotFoundException ex) {
		    // file does not exist
			
		} catch (IOException ex) {
		    // I/O error
		}
	}

	
	static class Action1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			storyMode();
		}
	}
	
	static class Action2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			weeklyMode();
		}
	}
	
	static class Action3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			subquestsMode();
		}
	}
	
	static class Action4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			doTheDailies();
		}
	}
	
	static class Action5 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			doItAll();
		}
	}

	static class Action6 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			clanHall();
		}
	}

	static class Action7 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			challenges();
		}
	}

	static class Action8 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			readMail();
		}
	}

	static class Action9 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			sortInv();
		}
	}

	static class Action10 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dungeonInit = true;
			dungeonRun();
		}
	}
	
	static class Action10b implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dungeonInit = false;
			dungeonRun();
		}
	}
	
	static class Action_Settings implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//score(DA_ELITE_DUNGEON_CURRENT_BTN);
			frame3.setVisible(true);
		}
	}
	
	static class Action_AcceptSettings implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			frame3.setVisible(false);
		}
	}	
	
	static class Action_ApplyScreen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setupScreens();
		}
	}	

	static class Action_ApplyScale implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setupGraphics();
		}
	}	
	
	static class Action_TestScale implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			accTest();
		}
	}	
	
	static class Action_AutoConfig implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			frame4.setVisible(true);
		}
	}	
	
	static class Action_AutoConfig_Apply implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setupScreen.setText(ac_screen.getText());
			setupScreens();
			setupScale.setText(ac_scale.getText());
			setupGraphics();
			setupOffsetX.setText(ac_offsetx.getText());
			setupOffsetY.setText(ac_offsety.getText());
			frame4.setVisible(false);
		}
	}	

	static class Action_AutoConfig_ApplyTest implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			Thread t = new Thread(() -> {
				log("PLEASE WAIT");
				setupScreen.setText(ac_screen.getText());
				setupScreens();
				setupScale.setText(ac_scale.getText());
				setupGraphics();
				log("APPLIED NEW GRAPHIC ELEMENTS");
				setupOffsetX.setText(ac_offsetx.getText());
				setupOffsetY.setText(ac_offsety.getText());
				frame4.setVisible(false);
				frame3.setVisible(false);
				exitMenues();
				log("GOING TO MAIL SCREEN..");
				goClick(BTN_MAIL_X, BTN_MAIL_Y);
				Sleep(2000);
				accTest();
				frame3.setVisible(true);
			});
			t.start();
		}
	}	

	
	static class ActionStop implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			auto = false;
			btn_stop.setEnabled(false);
			Sleep(4000);
			btn_AUTO.setEnabled(true);
			jobDone();
			busy = false;
			log("--- Thread STOPPED by user! ---");
		}
	}
	
	static class ActionSkip implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (auto) {
				if (autosequence == 1) done_da = true;
				if (autosequence == 2) done_we = true;
				if (autosequence == 3) done_sq = true;
				if (autosequence == 4) done_ms = true;
				if (autosequence == 5) done_cl = true;
				if (autosequence == 6) done_ch = true;
				if (autosequence == 7) done_rm = true;
				if (autosequence == 8) done_in = true;
				if (autosequence == 9) done_dr = true;
				jobDone(); 
				log("--- Sequence SKIPPED by user! ---");
			} else {
				skip = true;
				log("--- Thread SKIPPED by user! ---");
			}
		}
	}
	
	static class ActionExit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			quit();
		}
	}
	
	
	static class ActionDADungeonSel implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setupDA_dungeon();
		}
	}

	static class ActionDRDungeonSel implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setupDR_dungeon();
		}
	}
/*
	private static final String	DEF_BLUESTACKS_1200_SCALE = "100";
	private static final String	DEF_BLUESTACKS_1200_OFF_X = "12";
	private static final String	DEF_BLUESTACKS_1200_OFF_Y = "46";
	private static final String	DEF_BLUESTACKS_1080_SCALE = "88.5";
	private static final String	DEF_BLUESTACKS_1080_OFF_X = "12";
	private static final String	DEF_BLUESTACKS_1080_OFF_Y = "46";
*/	

	public static void presetLoad() {
		if (emulatorList.getSelectedIndex() == 0) {
			if (resolutionList.getSelectedIndex() == 0) {
				setupScale.setText(DEF_BLUESTACKS_1200_SCALE);
				setupOffsetX.setText(DEF_BLUESTACKS_1200_OFF_X);
				setupOffsetY.setText(DEF_BLUESTACKS_1200_OFF_Y);
			}
			if (resolutionList.getSelectedIndex() == 1) {
				setupScale.setText(DEF_BLUESTACKS_1080_SCALE);
				setupOffsetX.setText(DEF_BLUESTACKS_1080_OFF_X);
				setupOffsetY.setText(DEF_BLUESTACKS_1080_OFF_Y);
			}
		}
		if (emulatorList.getSelectedIndex() == 1) {
			if (resolutionList.getSelectedIndex() == 0) {
				setupScale.setText(DEF_MEMU_1200_SCALE);
				setupOffsetX.setText(DEF_MEMU_1200_OFF_X);
				setupOffsetY.setText(DEF_MEMU_1200_OFF_Y);
			}
			if (resolutionList.getSelectedIndex() == 1) {
				setupScale.setText(DEF_MEMU_1080_SCALE);
				setupOffsetX.setText(DEF_MEMU_1080_OFF_X);
				setupOffsetY.setText(DEF_MEMU_1080_OFF_Y);
			}
		}
		if (emulatorList.getSelectedIndex() == 2) {
			if (resolutionList.getSelectedIndex() == 0) {
				setupScale.setText(DEF_NOX_1200_SCALE);
				setupOffsetX.setText(DEF_NOX_1200_OFF_X);
				setupOffsetY.setText(DEF_NOX_1200_OFF_Y);
			}
			if (resolutionList.getSelectedIndex() == 1) {
				setupScale.setText(DEF_NOX_1080_SCALE);
				setupOffsetX.setText(DEF_NOX_1080_OFF_X);
				setupOffsetY.setText(DEF_NOX_1080_OFF_Y);
			}
		}

	}
	
	
	static class ActionEmuList implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			presetLoad();
		}
	}

	static class ActionResList implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			presetLoad();
		}
	}
	
	public static Pattern scaledImage(String file) {
		BufferedImage src = null;
		Pattern result = null;
		try {
			src = ImageIO.read(L2RBot.class.getResource(file));
			BufferedImage img = scaleImageByPercent(src, Float.parseFloat(setupScale.getText()));
			result = new Pattern(img);
		} catch (IOException | IllegalArgumentException | NullPointerException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static Pattern scaledImageLocalC(String file) {
		BufferedImage src = null;
		File sourceimage = new File("images\\configurable\\"+file);
		try {
			src = ImageIO.read(sourceimage);
		} catch (IOException e) {
			e.printStackTrace();
			log("IMAGE MISSING: images\\configurable\\"+file);
		}
		BufferedImage img = scaleImageByPercent(src, Float.parseFloat(setupScale.getText()));
		Pattern result = new Pattern(img);
		return result;
	}
	
	public static Pattern scaledImageLocal(String file) {
		BufferedImage src = null;
		File sourceimage = new File("images\\"+file);
		try {
			src = ImageIO.read(sourceimage);
		} catch (IOException e) {
			e.printStackTrace();
			log("IMAGE MISSING: images\\"+file);
		}
		BufferedImage img = scaleImageByPercent(src, Float.parseFloat(setupScale.getText()));
		Pattern result = new Pattern(img);
		return result;
	}
	
	public static BufferedImage scaleImageByPercent(BufferedImage image, float percent) {
		float percent1 = percent/100;
	    if ((percent1 <= 0.0F) || (percent1 == 1.0F)) // if the scale is either null, negative or none at all return the original image
	        return image;

	    Image scaledImage = image.getScaledInstance(
	            (int) (percent1 * image.getWidth()), -1, Image.SCALE_SMOOTH); // scale the input, smooth scaled
	    BufferedImage result = new BufferedImage(
	            scaledImage.getWidth(null), scaledImage.getHeight(null),
	            BufferedImage.TYPE_INT_RGB); // create the output image

	    result.getGraphics().drawImage(scaledImage, 0, 0, null); // draw the scaled image onto the output

	    return result;
	}
	
	static void accTest() {
		
		File sourceimage = new File("images\\configurable\\mail_box_acc_test.png");
		//File sourceimage = new File("images\\da_elited_ivory3.png");
		
		BufferedImage src = null;
		BufferedImage img = null;
		try {
			src = ImageIO.read(sourceimage);
			img = scaleImageByPercent(src, Float.parseFloat(setupScale.getText()));
		} catch (NullPointerException | IOException ex) {
			//ex.printStackTrace();
			log("Missing Test Image -> images\\configurable\\mail_box_acc_test.png");
			return;
		}
		
		Match m;
		try {
			//m = bbs.find(DUNGEON_RUN_CURRENT_BTN);
			m = bbs.find(new Pattern(img));
			String verbal;
			double score = m.getScore();
			if (score > 0.9999) verbal = "FLAWLESS!"; else
			if (score > 0.99) verbal = "EXCELLENT!"; else
			if (score > 0.97) verbal = "PERFECT!"; else
			if (score > 0.95) verbal = "VERY GOOD!"; else
			if (score > 0.93) verbal = "GOOD!"; else
			if (score > 0.9)  verbal = "OK"; else
			if (score > 0.85) verbal = "ACCEPTABLE!"; else
			if (score > 0.8)  verbal = "DECENT!"; else
			verbal = "TOO LOW!";
			log("TEST SCORE:"+score+" "+verbal);
		} catch (FindFailed e1) {
			//e1.printStackTrace();
			log("TEST SCORE: FAILED! Are you in the Mailbox screen ?");
		}

	}

	
	static void score(Pattern SP) {
		
		Match m;
		try {
			m = bbs.find(SP);
			String verbal;
			double score = m.getScore();
			if (score > 0.9999) verbal = "FLAWLESS!"; else
			if (score > 0.99) verbal = "EXCELLENT!"; else
			if (score > 0.97) verbal = "PERFECT!"; else
			if (score > 0.95) verbal = "VERY GOOD!"; else
			if (score > 0.93) verbal = "GOOD!"; else
			if (score > 0.9)  verbal = "OK"; else
			if (score > 0.85) verbal = "ACCEPTABLE!"; else
			if (score > 0.8)  verbal = "DECENT!"; else
			verbal = "TOO LOW!";
			log("TEST SCORE:"+score+" "+verbal);
		} catch (FindFailed e1) {
			e1.printStackTrace();
			log("TEST SCORE: FAILED!");
		}

	}
	
	
	static void loadMap(String title, String file, JTextField jx, JTextField jy, int diffx, int diffy) {
		lastMap = title;
		
		File sourceimage = new File("images\\"+file);
		try	{
			MAP_IMAGE = ImageIO.read(sourceimage);
			map_x_diff = mapsize - MAP_IMAGE.getWidth();
			map_y_diff = mapsize - MAP_IMAGE.getHeight();
			MAP_X_IMAGE = ImageIO.read(new File("images\\"+"map_x.png"));
		    Graphics g = MAP_IMAGE.getGraphics();
		    g.drawImage(MAP_X_IMAGE, Integer.parseInt(jx.getText())-diffx-map_x_half, Integer.parseInt(jy.getText())-diffy-map_x_half, null);
		}
		catch (Exception ex)
		{
		    //ex.printStackTrace();
		}
		
		label_map_image.setIcon(new ImageIcon(MAP_IMAGE));
		frameMap.setVisible(true);
	}
	
	
	
	static class ActionMapH1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("H1", "herbs_map.png", coordHerbs1X, coordHerbs1Y, herbs_map_diff_x, herbs_map_diff_y);
		}
	}
	static class ActionMapH2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("H2", "herbs_map.png", coordHerbs2X, coordHerbs2Y, herbs_map_diff_x, herbs_map_diff_y);
		}
	}
	static class ActionMapH3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("H3", "herbs_map.png", coordHerbs3X, coordHerbs3Y, herbs_map_diff_x, herbs_map_diff_y);
		}
	}

	
	
	static class ActionMapD1WP1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D1WP1", "dungeon_map_cruma3.png", D1_WP1X, D1_WP1Y, dungeon_map_cruma3_diff_x, dungeon_map_cruma3_diff_y);
		}
	}
	static class ActionMapD1WP2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D1WP2", "dungeon_map_cruma3.png", D1_WP2X, D1_WP2Y, dungeon_map_cruma3_diff_x, dungeon_map_cruma3_diff_y);
		}
	}
	static class ActionMapD1WP3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D1WP3", "dungeon_map_cruma3.png", D1_WP3X, D1_WP3Y, dungeon_map_cruma3_diff_x, dungeon_map_cruma3_diff_y);
		}
	}
	static class ActionMapD1WP4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D1WP4", "dungeon_map_cruma3.png", D1_WP4X, D1_WP4Y, dungeon_map_cruma3_diff_x, dungeon_map_cruma3_diff_y);
		}
	}
	static class ActionMapD1WP5 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D1WP5", "dungeon_map_cruma3.png", D1_WP5X, D1_WP5Y, dungeon_map_cruma3_diff_x, dungeon_map_cruma3_diff_y);
		}
	}
	
	
	static class ActionMapD2WP1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D2WP1", "dungeon_map_ivory1.png", D2_WP1X, D2_WP1Y, dungeon_map_ivory1_diff_x, dungeon_map_ivory1_diff_y);
		}
	}
	static class ActionMapD2WP2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D2WP2", "dungeon_map_ivory1.png", D2_WP2X, D2_WP2Y, dungeon_map_ivory1_diff_x, dungeon_map_ivory1_diff_y);
		}
	}
	static class ActionMapD2WP3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D2WP3", "dungeon_map_ivory1.png", D2_WP3X, D2_WP3Y, dungeon_map_ivory1_diff_x, dungeon_map_ivory1_diff_y);
		}
	}
	static class ActionMapD2WP4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D2WP4", "dungeon_map_ivory1.png", D2_WP4X, D2_WP4Y, dungeon_map_ivory1_diff_x, dungeon_map_ivory1_diff_y);
		}
	}
	static class ActionMapD2WP5 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D2WP5", "dungeon_map_ivory1.png", D2_WP5X, D2_WP5Y, dungeon_map_ivory1_diff_x, dungeon_map_ivory1_diff_y);
		}
	}
	
	
	static class ActionMapD3WP1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D3WP1", "dungeon_map_ivory2.png", D3_WP1X, D3_WP1Y, dungeon_map_ivory2_diff_x, dungeon_map_ivory2_diff_y);
		}
	}
	static class ActionMapD3WP2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D3WP2", "dungeon_map_ivory2.png", D3_WP2X, D3_WP2Y, dungeon_map_ivory2_diff_x, dungeon_map_ivory2_diff_y);
		}
	}
	static class ActionMapD3WP3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D3WP3", "dungeon_map_ivory2.png", D3_WP3X, D3_WP3Y, dungeon_map_ivory2_diff_x, dungeon_map_ivory2_diff_y);
		}
	}
	static class ActionMapD3WP4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D3WP4", "dungeon_map_ivory2.png", D3_WP4X, D3_WP4Y, dungeon_map_ivory2_diff_x, dungeon_map_ivory2_diff_y);
		}
	}
	static class ActionMapD3WP5 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D3WP5", "dungeon_map_ivory2.png", D3_WP5X, D3_WP5Y, dungeon_map_ivory2_diff_x, dungeon_map_ivory2_diff_y);
		}
	}	
	
	
	static class ActionMapD4WP1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D4WP1", "dungeon_map_ivory3.png", D4_WP1X, D4_WP1Y, dungeon_map_ivory3_diff_x, dungeon_map_ivory3_diff_y);
		}
	}
	static class ActionMapD4WP2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D4WP2", "dungeon_map_ivory3.png", D4_WP2X, D4_WP2Y, dungeon_map_ivory3_diff_x, dungeon_map_ivory3_diff_y);
		}
	}
	static class ActionMapD4WP3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D4WP3", "dungeon_map_ivory3.png", D4_WP3X, D4_WP3Y, dungeon_map_ivory3_diff_x, dungeon_map_ivory3_diff_y);
		}
	}
	static class ActionMapD4WP4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D4WP4", "dungeon_map_ivory3.png", D4_WP4X, D4_WP4Y, dungeon_map_ivory3_diff_x, dungeon_map_ivory3_diff_y);
		}
	}
	static class ActionMapD4WP5 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D4WP5", "dungeon_map_ivory3.png", D4_WP5X, D4_WP5Y, dungeon_map_ivory3_diff_x, dungeon_map_ivory3_diff_y);
		}
	}
	
	
	
	static class ActionMapD5WP1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D5WP1", "dungeon_map_fos_canopy.png", D5_WP1X, D5_WP1Y, dungeon_map_foscan_diff_x, dungeon_map_foscan_diff_y);
		}
	}
	static class ActionMapD5WP2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D5WP2", "dungeon_map_fos_canopy.png", D5_WP2X, D5_WP2Y, dungeon_map_foscan_diff_x, dungeon_map_foscan_diff_y);
		}
	}
	static class ActionMapD5WP3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D5WP3", "dungeon_map_fos_canopy.png", D5_WP3X, D5_WP3Y, dungeon_map_foscan_diff_x, dungeon_map_foscan_diff_y);
		}
	}
	static class ActionMapD5WP4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D5WP4", "dungeon_map_fos_canopy.png", D5_WP4X, D5_WP4Y, dungeon_map_foscan_diff_x, dungeon_map_foscan_diff_y);
		}
	}
	static class ActionMapD5WP5 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D5WP5", "dungeon_map_fos_canopy.png", D5_WP5X, D5_WP5Y, dungeon_map_foscan_diff_x, dungeon_map_foscan_diff_y);
		}
	}
	
	
	
	static class ActionMapD6WP1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D6WP1", "dungeon_map_fos_under.png", D6_WP1X, D6_WP1Y, dungeon_map_fosund_diff_x, dungeon_map_fosund_diff_y);
		}
	}
	static class ActionMapD6WP2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D6WP2", "dungeon_map_fos_under.png", D6_WP2X, D6_WP2Y, dungeon_map_fosund_diff_x, dungeon_map_fosund_diff_y);
		}
	}
	static class ActionMapD6WP3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D6WP3", "dungeon_map_fos_under.png", D6_WP3X, D6_WP3Y, dungeon_map_fosund_diff_x, dungeon_map_fosund_diff_y);
		}
	}
	static class ActionMapD6WP4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D6WP4", "dungeon_map_fos_under.png", D6_WP4X, D6_WP4Y, dungeon_map_fosund_diff_x, dungeon_map_fosund_diff_y);
		}
	}
	static class ActionMapD6WP5 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadMap("D6WP5", "dungeon_map_fos_under.png", D6_WP5X, D6_WP5Y, dungeon_map_fosund_diff_x, dungeon_map_fosund_diff_y);
		}
	}
	
	
	static void quit() {
		/*
		if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			saveSettings();
			System.exit(0);
		}
		*/
		saveSettings();
		System.exit(0);
	}

	private static boolean checkSkip() {
		if (skip) {
			skip = false;
			return true;
		}
		return false;
	}
	
	private static void lfac(String tit, Pattern img, int sec, boolean critical, float ACC) {
		if (!running) return;
		
		boolean wrote = false;
		boolean search = true;
		int ctr = sec;
		try {
			while (search && running) {
				if (bbs.exists(img.similar(ACC)) != null) {
					bbs.click(img.similar(ACC));
					search = false;
					//log("[o] "+tit);
					if (critical) {
						logsl(" [✓]");
						 wrote = true;
					}
				}
				if (ctr <= 0) {
					search = false;
					if (critical) {
						running = false;
						logsl(" [X] NOT FOUND");
						 wrote = true;
					}
				}
	
				ctr--;
				if (search) bbs.wait(1.0);
				if (search && ctr == sec-1) { logsl("Looking for: " + tit + "."); wrote = true; }
				if (search && ctr < sec-1) { logsl(".");  wrote = true; }
				if (checkSkip()) return;
			}
			
		} catch (FindFailed e) {
			log("FF:"+tit);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		if (wrote) logsl("\n");
		//bbs.wait(img.similar((float) 0.90), 60).click();
	}
	
	private static void lfac_nr(Pattern img, float ACC) {
		int x = 300;
		while (x > 0) {
			try {
				if (bbs.exists(img.similar(ACC)) != null) {
					bbs.click(img.similar(ACC));
					x=1;
				} 
			} catch (FindFailed e) {
			}
			x--;
			bbs.wait(1.0);
		}
	}
	
	public static void pressButton(Pattern bp, String txt) {
		Match m;
		try {
			log("--- STARTING DA: "+txt);
			m = bbs.find(bp.similar(ACC_HIGH));
			int x = m.getX();
			int y = m.getY();
			if (running) bbs.click(new Location(x+BTN_DA_OFFSET*Float.parseFloat(setupScale.getText())/100, y+70*Float.parseFloat(setupScale.getText())/100));
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}
	
	public static void do1v1(Pattern bp, String txt) {
		Match m;
		try {
			log("--- STARTING ARENA MATCH: "+txt);
			m = bbs.find(bp.similar(ACC_PVP));
			int x = m.getX();
			int y = m.getY();
			if (running) bbs.click(new Location(x+BTN_ARENA_OFFSET*Float.parseFloat(setupScale.getText())/100, y));
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	public static void slide(Pattern from, Pattern to) {
		Match start;
		Match end;
		try {
			start = bbs.find(from.similar(ACCURACY));
			end = bbs.find(to.similar(ACCURACY));
			if (running) bbs.dragDrop(start, end);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}
	
	public static void slidec(int x1, int y1, int x2, int y2) {
		
		float nx1 = DEFX+x1*Float.parseFloat(setupScale.getText())/100;
		float ny1 = y1*Float.parseFloat(setupScale.getText())/100;
		float nx2 = DEFX+x2*Float.parseFloat(setupScale.getText())/100;
		float ny2 = y2*Float.parseFloat(setupScale.getText())/100;
		
		nx1 = nx1-OFFSETX_INIT+Integer.parseInt(setupOffsetX.getText());
		ny1 = ny1-OFFSETY_INIT+Integer.parseInt(setupOffsetY.getText());
		nx2 = nx2-OFFSETX_INIT+Integer.parseInt(setupOffsetX.getText());
		ny2 = ny2-OFFSETY_INIT+Integer.parseInt(setupOffsetY.getText());
		
		try {
			bbs.dragDrop(new Location(nx1, ny1), new Location(nx2, ny2));
		} catch (FindFailed e) {
			e.printStackTrace();
		} 
	}
	
	static void log(String txt) {
		logger.log(Level.INFO, txt);
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		talog.append(timeStamp+" "+txt+"\n");
		infoBox.setText(txt);
	}
	
	static void logsl(String txt) {
		talog.append(txt);
	}
	
	static void Sleep(int time) {
		double smod = Double.parseDouble(setupSleep.getText());
		if (smod < 0.5) smod = 0.5;
		if (smod > 2) smod = 2;
		long finalduration = Math.round(time*smod);
		try {
			Thread.sleep(finalduration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void Sleep2(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	static void start() {
		
		running = true;
		busy = true;
		dungeonRunMode = false;
		
		btn_main_quest.setEnabled(false);
		btn_weekly.setEnabled(false);
		btn_subquests.setEnabled(false);
		btn_da.setEnabled(false);
		
		btn_ch_stuff.setEnabled(false);
		btn_challenges.setEnabled(false);
		btn_mail.setEnabled(false);
		btn_inv.setEnabled(false);
		btn_dr.setEnabled(false);
		btn_dr_noinit.setEnabled(false);
		btn_settings.setEnabled(false);
		
		btn_AUTO.setEnabled(false);
		
		btn_stop.setEnabled(true);
		btn_skip.setEnabled(true);
		
		//if (screen == 1) DEFX = 1920; else DEFX = 0;
		
	}
	
	static void jobDone() {
		running = false;
		dungeonRunMode = false;
		
		if (!auto) {
			btn_main_quest.setEnabled(true);
			btn_weekly.setEnabled(true);
			btn_subquests.setEnabled(true);
			btn_da.setEnabled(true);
			
			btn_ch_stuff.setEnabled(true);
			btn_challenges.setEnabled(true);
			btn_mail.setEnabled(true);
			btn_inv.setEnabled(true);
			btn_dr.setEnabled(true);
			btn_dr_noinit.setEnabled(true);
			btn_settings.setEnabled(true);
			
			btn_AUTO.setEnabled(true);
			
			btn_stop.setEnabled(false);
			btn_skip.setEnabled(false);
		}
	}
	
	static void lockButtons() {
		btn_main_quest.setEnabled(false);
		btn_weekly.setEnabled(false);
		btn_subquests.setEnabled(false);
		btn_da.setEnabled(false);
		
		btn_ch_stuff.setEnabled(false);
		btn_challenges.setEnabled(false);
		btn_mail.setEnabled(false);
		btn_inv.setEnabled(false);
		btn_dr.setEnabled(false);
		btn_dr_noinit.setEnabled(false);
		btn_settings.setEnabled(false);
		
		btn_stop.setEnabled(false);
		btn_skip.setEnabled(false);
		
		btn_AUTO.setEnabled(false);
		btn_exit.setEnabled(false);
	}

	static void unlockButtons() {
		btn_main_quest.setEnabled(true);
		btn_weekly.setEnabled(true);
		btn_subquests.setEnabled(true);
		btn_da.setEnabled(true);
		
		btn_ch_stuff.setEnabled(true);
		btn_challenges.setEnabled(true);
		btn_mail.setEnabled(true);
		btn_inv.setEnabled(true);
		btn_dr.setEnabled(true);
		btn_dr_noinit.setEnabled(true);
		btn_settings.setEnabled(true);
		
		btn_AUTO.setEnabled(true);
		btn_exit.setEnabled(true);
	}
	

	static void resetDA() {
		ez_DA_FREE_DRAW = false;
		ez_DA_TOI = false;
		ez_DA_FRIENDS = false;
		ez_DA_DD = false;
		ez_DA_CLAN_DONATION = false;
		ez_DA_CLAN_SHOP = false;
		ez_DA_CLAN_CHECKIN = false;
		ez_DA_CLAN_MEMBERS = false;
		ez_DA_ARENA = false;
		ez_DA_ELITE_DUNGEON = false;
		ez_DA_HERBS = false;
		ez_DA_FREE_HG = false;
		ez_DA_TEMPLE = false;
		ez_DA_VAULT = false;
		ez_DA_TRIALS = false;
		ez_DA_CIRCLE = false;
	}
	
	static void exitMenues(){
		if (bbs.exists(EXIT) != null) {
			lfac("EXIT", EXIT, DEFDELAY, false, ACCURACY);
			Sleep(2000);
		} else
		if (bbs.exists(IS_IN_MENU.similar(ACCURACY)) != null) {
			if (running) goClick(200, 800);
			Sleep(2000);
		}
	}
	
	static void goClick(int x, int y) {
		
		//log("goClick:"+x+"|"+y);
		
		float nx = DEFX+x*Float.parseFloat(setupScale.getText())/100;
		float ny = y*Float.parseFloat(setupScale.getText())/100;
		
		nx = nx-OFFSETX_INIT+Integer.parseInt(setupOffsetX.getText());
		ny = ny-OFFSETY_INIT+Integer.parseInt(setupOffsetY.getText());
		
		try {
			bbs.click(new Location(nx, ny));
		} catch (FindFailed e) {
			//e.printStackTrace();
		}
	}
	
	static void goClick2(int x, int y) {
		
		//log("goClick:"+x+"|"+y);
		
		float nx = DEFX+x;
		float ny = y;

		try {
			bbs.click(new Location(nx, ny));
		} catch (FindFailed e) {
			//e.printStackTrace();
		}
	}
	
	public static void storyMode(){

		log("--- Story Mode");
		start();
		done_ms = false;
		
		Thread t = new Thread(() -> {
		
			int storyBtnX = 200;
			int storyBtnY = 450;
			
			exitMenues();
			
			if (bbs.exists(MQ_LOCKED.similar(ACCURACY)) != null || bbs.exists(MQ_LOCKED2.similar(ACCURACY)) != null) {
				done_ms = true;
				jobDone();
			}
			
			log("Starting Q:1..");
			if (running) goClick(storyBtnX, storyBtnY); //start story mode
			if (running) Sleep(2000);
			if (running && bbs.exists(MQ_WALK_TOWARDS.similar(ACCURACY)) != null) {
				if (running) lfac("MQ_WALK_TOWARDS", MQ_WALK_TOWARDS, 1, false, ACCURACY);
			}
			
			int counter = 0;
			int deathcount = 0;
			while (running) { // loop this event
				
				if (running && bbs.exists(MQ_BOX_USE.similar(ACCURACY)) != null) {
					if (running) lfac("MQ_BOX_USE", MQ_BOX_USE, DEFDELAY, false, ACCURACY);
				}
				
				if (running && bbs.exists(MQ_CLAIM_REWARD.similar(ACCURACY)) != null) {
					if (running) lfac("MQ_CLAIM_REWARD", MQ_CLAIM_REWARD, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
					if (bbs.exists(MQ_LOCKED.similar(ACCURACY)) != null || bbs.exists(MQ_LOCKED2.similar(ACCURACY)) != null) {
						done_ms = true;
						jobDone();
					}
					counter++;
					dailyTimer = 19;
					checkOnDaily();
					if (running) Sleep(1000);
					if (running) goClick(storyBtnX, storyBtnY); //start story mode
					if (running) Sleep(1000);
							
				}
				if (running && bbs.exists(MQ_ACCEPT_QUEST.similar(ACCURACY)) != null) {
					if (running) lfac("ACCEPT_QUEST", MQ_ACCEPT_QUEST, DEFDELAY, false, ACCURACY);
					log("--- Story Mode Q:"+(counter+1));
				}
				
				if (running && (bbs.exists(MQ_SPOT_REVIVAL.similar(ACCURACY)) != null || bbs.exists(MQ_DEATH_X.similar(ACCURACY)) != null)) {
					if (running && bbs.exists(MQ_SPOT_REVIVAL.similar(ACCURACY)) != null) {
						if (running) lfac("MQ_SPOT_REVIVAL", MQ_SPOT_REVIVAL, DEFDELAY, false, ACCURACY);
						if (running) Sleep(4000);
						if (running) goClick(1840, 430); //X
						if (running) Sleep(2000);
					} else {
						if (running) lfac("MQ_DEATH_X", MQ_DEATH_X, DEFDELAY, false, ACCURACY);
						if (running) Sleep(2000);
					}
					if (running) goClick(storyBtnX, storyBtnY); //start story mode
					if (running) Sleep(2000);
					deathcount++;
					if (deathcount > 4) {
						log("DIED 5 TIMES ALREADY.. SOMETHINGS OFF.. STOPPING");
						done_ms = true;
						jobDone();
					}
				}
				
				if (running && bbs.exists(MQ_WALK_TOWARDS.similar(ACCURACY)) != null) {
					if (running) lfac("MQ_WALK_TOWARDS", MQ_WALK_TOWARDS, 1, false, ACCURACY);
				}					
				if (running && (bbs.exists(MQ_QUEST_ARROWS) != null || bbs.exists(MQ_QUEST_SKIP) != null)) {
					if (running) goClick(1820, 820); //skip
					if (running) Sleep(2000);
					if (bbs.exists(MQ_LOCKED.similar(ACCURACY)) != null || bbs.exists(MQ_LOCKED2.similar(ACCURACY)) != null) {
						done_ms = true;
						jobDone();
					}
					if (running) goClick(storyBtnX, storyBtnY); //start story mode
					if (running) Sleep(2000);
					if (bbs.exists(MQ_ACCEPT_QUEST.similar(ACCURACY)) != null) {
						if (running) lfac("ACCEPT_QUEST", MQ_ACCEPT_QUEST, DEFDELAY, false, ACCURACY);
						log("--- Story Mode Q:"+(counter+1));
					}
				}
				if (running && bbs.exists(MQ_CONTINUE.similar(ACCURACY)) != null) {
					if (running) lfac("MQ_CONTINUE", MQ_CONTINUE, DEFDELAY, false, ACCURACY);
					if (running) Sleep2(10000);
					if (bbs.exists(MQ_LOCKED.similar(ACCURACY)) != null || bbs.exists(MQ_LOCKED2.similar(ACCURACY)) != null) {
						done_ms = true;
						jobDone();
					}
					if (running) goClick(storyBtnX, storyBtnY); //start story mode
					if (running) Sleep(2000);
				}
				
				if (running) Sleep(500);
			}
			
			log("--- Story Mode Done..");

			busy = false;

		});
		t.start();
		
	}
	
	public static void buyScroll() {
		if (running && bbs.exists(SUBQ_A.similar(ACC_095)) == null && cb_SUBQUEST_BUY_SCROLL_A.isSelected()) {
			if (running) log(">>Need to buy Scroll A.");
			if (running) lfac("SUBQ_X", SUBQ_X, DEFDELAY, false, ACCURACY);
			if (running) Sleep(2000);
			if (running) goClick(BTN_SHOP_X, BTN_SHOP_Y); //shop
			if (running) Sleep2(5000);
			if (running) lfac("SUBQ_SCROLL", SUBQ_SCROLL, DEFDELAY, false, ACCURACY);
			if (running) Sleep(2000);
			if (running && bbs.exists(SUBQ_SOLD_OUT.similar(ACCURACY)) != null) {
				if (running) log(">>Scroll A sold out! Will try to use what we have.");
			} else {
				if (running) lfac("SUBQ_BUYSCROLL", SUBQ_BUYSCROLL, DEFDELAY, false, ACC_095);
				if (running) Sleep(2000);
				if (running) lfac("SUBQ_BUYSCROLL_BUY", SUBQ_BUYSCROLL_BUY, DEFDELAY, false, ACCURACY);
				if (running) Sleep2(5000);
			}
			if (running) lfac("EXIT", EXIT, DEFDELAY, false, ACCURACY);
			if (running) Sleep(2000);
			if (running) lfac("SUBQ_AVAIL", SUBQ_AVAIL, DEFDELAY, false, ACCURACY);
			if (running) Sleep(2000);
		} else {
			//TODO if no scrolls exit 
		}
	}

	public static void subquestsMode(){

		start();
		done_sq = false;
		
		Thread t = new Thread(() -> {
			
			exitMenues();

			if (running && bbs.exists(SUBQ_AVAIL.similar(ACCURACY)) != null) {
				log("--- Subquest Mode Q:1");
			} else
			if (running && bbs.exists(SUBQ_RESET) != null) {

			} else
			if (running && bbs.exists(SUBQ_CONT) != null) {
				if (running) lfac("SUBQ_CONT", SUBQ_CONT, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				if (running) log("Continuing Subquest..");
				if (running && bbs.exists(WEEKLY_WALK.similar(ACCURACY)) != null) {
					if (running) lfac("WEEKLY_WALK", WEEKLY_WALK, DEFDELAY, false, ACCURACY);
				}
			}
			
			int counter = 0;
			int deathcount = 0;
			while (running) { // loop this event

				if (running && bbs.exists(SUBQ_AVAIL.similar(ACCURACY)) != null) {
					if (running) log("Starting Subquest..");
					if (running) lfac("SUBQ_AVAIL", SUBQ_AVAIL, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
					buyScroll();
					if (running) lfac("SUBQ_FF", SUBQ_FF, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
					if (running && bbs.exists(SUBQ_NOTAVAIL.similar(ACC_095)) != null) {
						if (running) lfac("SUBQ_OK", SUBQ_OK, DEFDELAY, false, ACCURACY);
						if (running) Sleep(2000);
						if (running) goClick(850, 400); //next scroll
						buyScroll();
						if (running) lfac("SUBQ_FF", SUBQ_FF, DEFDELAY, false, ACCURACY);
						if (running) Sleep(2000);
					}
					if (running) lfac("SUBQ_OK", SUBQ_OK, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
					if (running) lfac("SUBQ_START", SUBQ_START, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
					if (running && bbs.exists(WEEKLY_WALK.similar(ACCURACY)) != null) {
						if (running) lfac("WEEKLY_WALK", WEEKLY_WALK, DEFDELAY, false, ACCURACY);
					}
					log("--- Subquest Mode Q:"+(counter+1));
				}
			
				if (running && (bbs.exists(MQ_QUEST_ARROWS) != null || bbs.exists(MQ_QUEST_SKIP) != null)) {
					if (running) goClick(1820, 820); //skipquest
					if (running) Sleep(2000);
				}
				if (running && bbs.exists(MQ_CLAIM_REWARD) != null) {
					if (running) lfac("CLAIM_REWARD", MQ_CLAIM_REWARD, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
					counter++;
					if (running) {
						dailyTimer = 19;
						checkOnDaily();
					}
				}
				
				if (running && bbs.exists(SUBQ_RESET) != null) {
					if (running) log(">>Max Subquests count reached.");
					if (running && cb_SUBQUEST_RESET.isSelected()) {
						if (running) lfac("SUBQ_RESET", SUBQ_RESET, DEFDELAY, false, ACCURACY);
						if (running) Sleep(2000);
						if (running && bbs.exists(SUBQ_RESET_COUNT50.similar(ACC_HIGH)) != null) {
							if (running) lfac("SUBQ_RESET_RESET", SUBQ_RESET_RESET, DEFDELAY, false, ACCURACY);
							if (running) log(">>Subquests reset using gems!");
							if (running) Sleep(2000);
						} else {
							if (running) log(">>Reset is too expensive. Cancelled!");
							if (running) lfac("SUBQ_RESET_CANCEL", SUBQ_RESET_CANCEL, DEFDELAY, false, ACCURACY);
							done_sq = true;
							jobDone();
							busy = false;
							break;
						}
					} else {
						if (running) log(">>Subquest reset disabled. We are done here!");
						done_sq = true;
						jobDone();
						busy = false;
						break;
					}
				}
				
				if (running && (bbs.exists(MQ_SPOT_REVIVAL.similar(ACCURACY)) != null || bbs.exists(MQ_DEATH_X.similar(ACCURACY)) != null)) {
					if (running && bbs.exists(MQ_SPOT_REVIVAL.similar(ACCURACY)) != null) {
						if (running) lfac("MQ_SPOT_REVIVAL", MQ_SPOT_REVIVAL, DEFDELAY, false, ACCURACY);
						if (running) Sleep(4000);
						if (running) goClick(1840, 430); //X
						if (running) Sleep(2000);
					} else {
						if (running) lfac("MQ_DEATH_X", MQ_DEATH_X, DEFDELAY, false, ACCURACY);
						if (running) Sleep(2000);
					}
					
					if (running && bbs.exists(SUBQ_CONT) != null) {
						if (running) lfac("SUBQ_CONT", SUBQ_CONT, DEFDELAY, false, ACCURACY);
						if (running) Sleep(2000);
					}
					
					if (running) Sleep(2000);
					deathcount++;
					if (deathcount > 4) {
						log("DIED 5 TIMES ALREADY.. SOMETHINGS OFF.. STOPPING");
						done_sq = true;
						jobDone();
						busy = false;
						break;
					}
				}
				
				if (running) Sleep(1000);
			}
			
			log("--- Subquest Mode Done..");

			busy = false;

		});
		t.start();
		
	}

	public static void weeklyMode(){

		log("--- Weekly Mode Q:1");
		start();
		done_we = false;
		
		Thread t = new Thread(() -> {
			
			exitMenues();

			int counter = 0;
			
			if (running) lfac("QUESTLOG_START", QUESTLOG_START, 2, false, ACCURACY);
			if (running) Sleep(2000);
			if (running) lfac("WEEKLY_QUESTS", WEEKLY_QUESTS, 2, false, ACCURACY);
			if (running) Sleep(2000);
			
			if (running && bbs.exists(WEEKLY_QUEST_COMPLETE.similar(ACC_HIGH)) != null) {
				if (running) lfac("WEEKLY_QUEST_COMPLETE", WEEKLY_QUEST_COMPLETE, DEFDELAY, false, ACC_HIGH);
				if (running) Sleep(2000);
				counter++;
				if (running && counter <= 10) log("--- Weekly Mode Q:"+(counter+1));
			}
			
			if (running && bbs.exists(WEEKLY_COMPLETE.similar(ACCURACY)) != null) {
				done_we = true;
				jobDone();
			}
			
			if (running && bbs.exists(WEEKLY_START_QUEST.similar(ACC_HIGH)) != null) {
				if (running) lfac("WEEKLY_START_QUEST", WEEKLY_START_QUEST, 3, false, ACC_HIGH);
				if (running) Sleep(2000);
			}
			if (running && bbs.exists(WEEKLY_GO_NOW.similar(ACCURACY)) != null) {
				if (running) lfac("WEEKLY_GO_NOW", WEEKLY_GO_NOW, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
			}
			if (running && bbs.exists(WEEKLY_WALK.similar(ACCURACY)) != null) {
				if (running) lfac("WEEKLY_WALK", WEEKLY_WALK, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
			}
			
			int cctr = 0;
			while (running) { // loop this event
			
				if (running && bbs.exists(WEEKLY_DONE.similar(ACCURACY)) != null || cctr == 20) {
					cctr = 0;
					
					if (running) lfac("WEEKLY_DONE", WEEKLY_DONE, 2, false, ACCURACY);
					if (running) Sleep(2000);
					if (running && bbs.exists(PAGE_IS_DAILY_QUESTS) != null) {
						dailyStuff();
					} else {
						if (running && bbs.exists(WEEKLY_QUEST_COMPLETE.similar(ACCURACY)) != null) {
							if (running) lfac("WEEKLY_QUEST_COMPLETE", WEEKLY_QUEST_COMPLETE, DEFDELAY, false, ACCURACY);
							if (running) Sleep(2000);
							counter++;
							log("--- Weekly Mode Q:"+(counter+1));
							if (counter == 10) {
								done_we = true;
								jobDone();
								busy = false;
								break;
							}
						}
						if (running && bbs.exists(WEEKLY_COMPLETE.similar(ACCURACY)) != null) {
							done_we = true;
							jobDone();
							busy = false;
							break;
						}
						
						if (running && bbs.exists(WEEKLY_START_QUEST.similar(ACC_HIGH)) != null) {
							if (running) lfac("WEEKLY_START_QUEST", WEEKLY_START_QUEST, 3, false, ACC_HIGH);
							if (running) Sleep(2000);
						}
						if (running && bbs.exists(WEEKLY_GO_NOW.similar(ACCURACY)) != null) {
							if (running) lfac("WEEKLY_GO_NOW", WEEKLY_GO_NOW, DEFDELAY, false, ACCURACY);
							if (running) Sleep(2000);
						}
						if (running && bbs.exists(WEEKLY_WALK.similar(ACCURACY)) != null) {
							if (running) lfac("WEEKLY_WALK", WEEKLY_WALK, DEFDELAY, false, ACCURACY);
							if (running) Sleep(2000);
						}
					}
					cctr++;
				}
				
				if (running && (bbs.exists(MQ_SPOT_REVIVAL.similar(ACCURACY)) != null || bbs.exists(MQ_DEATH_X.similar(ACCURACY)) != null)) {
					if (running && bbs.exists(MQ_SPOT_REVIVAL.similar(ACCURACY)) != null) {
						if (running) lfac("MQ_SPOT_REVIVAL", MQ_SPOT_REVIVAL, DEFDELAY, false, ACCURACY);
						if (running) Sleep(4000);
						if (running) goClick(1840, 430); //X
						if (running) Sleep(2000);
					} else {
						if (running) lfac("MQ_DEATH_X", MQ_DEATH_X, DEFDELAY, false, ACCURACY);
						if (running) Sleep(2000);
					}
					
					if (running) goClick(130, 650); //cont weekly
					if (running) Sleep(2000);
					if (bbs.exists(MQ_WALK_TOWARDS.similar(ACCURACY)) != null) {
						if (running) lfac("MQ_WALK_TOWARDS", MQ_WALK_TOWARDS, DEFDELAY, false, ACCURACY);
					}
				}
				
				if (running) checkOnDaily();
				
				if (running) Sleep(3000);
			}
			
			log("--- Weekly Mode Done..");
			done_we = true;
			busy = false;
			
		});
		t.start();
		
	}

	public static String dailies() {
		
		if (ez_DA_FREE_DRAW) {
			
		} else
		if (running && cb_DA_FREE_DRAW.isSelected() && bbs.exists(DA_FREE_DRAW.similar(ACC_HIGH)) != null) {
			if (running) pressButton(DA_FREE_DRAW, "FREE DRAW");
			if (running) Sleep2(5000);
			
			if (running && bbs.exists(DA_BAD_IMAGE_X) != null) {
				if (running) lfac("DA_BAD_IMAGE_X", DA_BAD_IMAGE_X, 3, false, ACCURACY);
				if (running) Sleep(3000);
			}
			
			if (running && bbs.exists(DA_FREE_BOX) == null) {
				if (running) lfac("DA_FREE_DRAW_FP", DA_FREE_DRAW_FP, 3, false, ACCURACY);
			}
			if (running && bbs.exists(DA_FREE_BOX) != null) {
				if (running) lfac("DA_FREE_BOX", DA_FREE_BOX, DEFDELAY, false, ACCURACY);
				if (running) Sleep2(5000);
				if (running) lfac("DA_FREE_BOX_OPEN", DA_FREE_BOX_OPEN, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				if (running) lfac("DA_FREE_BOX_OPEN_OK", DA_FREE_BOX_OPEN_OK, DEFDELAY, false, ACCURACY);
				if (running) log("> free draw complete..");
				if (running) ez_DA_FREE_DRAW = true;
				return "cont";
			} else {
				if (running) ez_DA_FREE_DRAW = true;
				return "cont";
			}

		} else {
			if (running) log("> no free draw ..");
			//if (running) ez_DA_FREE_DRAW = true;
		}
		
		if (running) Sleep(4000);
		if (ez_DA_TOI) {
			
		} else
		if (running && cb_DA_TOI.isSelected() && bbs.exists(DA_TOI.similar(ACC_HIGH)) != null) {
			if (running) pressButton(DA_TOI, "TOWER OF INSOLENCE");
			if (running) Sleep(2000);
			if (running) lfac("DA_TOI_AC", DA_TOI_AC, DEFDELAY, false, ACCURACY);
			if (running) Sleep(4000);
			if (running) lfac("DA_TOI_AC_OK", DA_TOI_AC_OK, 30, false, ACCURACY);
			if (running) log("> toi complete..");
			if (running) ez_DA_TOI = true;
			return "cont";
		} else {
			if (running) log("> no toi ..");
			//if (running) ez_DA_TOI = true;
		}

		if (ez_DA_FRIENDS) {
			
		} else
		if (running && cb_DA_FRIENDS.isSelected() && bbs.exists(DA_FRIENDS.similar(ACC_HIGH)) != null) {
			if (running) pressButton(DA_FRIENDS, "GREET FRIENDS");
			if (running) Sleep(2000);
			if (running) lfac("DA_FRIENDS_GA", DA_FRIENDS_GA, 1, false, ACCURACY);
			if (running) Sleep(2000);
			if (bbs.exists(DA_FRIENDS_OK.similar(ACCURACY)) != null) {
				if (running) lfac("DA_FRIENDS_OK", DA_FRIENDS_OK, DEFDELAY, false, ACCURACY);
			}
			if (running) lfac("DA_FRIENDS_CA", DA_FRIENDS_CA, 1, false, ACCURACY);
			if (bbs.exists(DA_FRIENDS_OK.similar(ACCURACY)) != null) {
				if (running) lfac("DA_FRIENDS_OK", DA_FRIENDS_OK, DEFDELAY, false, ACCURACY);
			}
			if (running) log("> friends greet complete..");
			if (running) ez_DA_FRIENDS = true;
			return "cont";
		} else {
			if (running) log("> no friends greet..");
			//if (running) ez_DA_FRIENDS = true;
		}
		
		if (ez_DA_DD) {
			
		} else
		if (running && cb_DA_DD.isSelected() && bbs.exists(DA_DD.similar(ACC_HIGH)) != null) {
			if (running) pressButton(DA_DD, "DAILY DUNGEON");
			if (running) log("> waiting 8s in case manual diff change needed..");
			if (running) Sleep2(8000);
			if (running) lfac("DA_DD_ENTER", DA_DD_ENTER, DEFDELAY, false, ACCURACY);
			if (running) log("> starting daily dungeon..");
			if (running) Sleep(2000);
			if (running) lfac("DA_DD_OK", DA_DD_OK, 300, false, ACCURACY); //5 min wait for dungeon complete
			if (running) log("> daily dungeon complete..");
			if (running) Sleep2(DEF_STAGE_PAUSE);
			if (running) ez_DA_DD = true;
			return "cont";
		} else {
			if (running) log("> no daily dungeon ..");
			//if (running) ez_DA_DD = true;
		}

		if (ez_DA_CLAN_CHECKIN && ez_DA_CLAN_MEMBERS) {
			
		} else
		if (!cb_DA_CLAN_CHECKIN.isSelected() && !cb_DA_CLAN_MEMBERS.isSelected()) {
			if (running) log("> no clan stuff ..");
		} else
		if (running && inclan && bbs.exists(DA_CLAN.similar(ACC_HIGH)) != null) {
			if (running) pressButton(DA_CLAN, "CLAN ACTIVITIES");
			if (running) Sleep(2000);
			
			if (!cb_DA_CLAN_CHECKIN.isSelected() || ez_DA_CLAN_CHECKIN) {
				ez_DA_CLAN_CHECKIN = true;
			} else
			if (bbs.exists(DA_CLAN_CHECKIN.similar(ACC_095)) != null) {
				if (running) lfac("DA_CLAN_CHECKIN", DA_CLAN_CHECKIN, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				if (running) lfac("DA_CLAN_CHECKIN_REWARD", DA_CLAN_CHECKIN_REWARD, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				if (running) lfac("DA_CLAN_CHECKIN_REWARD_OK", DA_CLAN_CHECKIN_REWARD_OK, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				if (running) log("--- clan check in done !");
				if (running) ez_DA_CLAN_CHECKIN = true;
			} else {
				if (running) log("--- clan check in already done !");
				if (running) ez_DA_CLAN_CHECKIN = true;
			}

			if (!cb_DA_CLAN_MEMBERS.isSelected() || ez_DA_CLAN_MEMBERS) {
				ez_DA_CLAN_MEMBERS = true;
			} else
			if (running && bbs.exists(DA_CLAN_MEMBERS.similar(ACC_095)) != null) {
				if (running) lfac("DA_CLAN_MEMBERS", DA_CLAN_MEMBERS, DEFDELAY, false, ACCURACY);
				if (running) Sleep(4000);
				if (running) lfac("DA_CLAN_MEMBERS_GA", DA_CLAN_MEMBERS_GA, DEFDELAY, false, ACCURACY);
				if (running) Sleep2(5000);
				if (running && bbs.exists(DA_CLAN_MEMBERS_OK.similar(ACCURACY)) != null) {
					if (running) lfac("DA_CLAN_MEMBERS_OK", DA_CLAN_MEMBERS_OK, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
				}
				if (running) lfac("DA_CLAN_MEMBERS_CA", DA_CLAN_MEMBERS_CA, DEFDELAY, false, ACCURACY);
				if (running) Sleep2(5000);
				if (running && bbs.exists(DA_CLAN_MEMBERS_OK.similar(ACCURACY)) != null) {
					if (running) lfac("DA_CLAN_MEMBERS_OK", DA_CLAN_MEMBERS_OK, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
				}
				if (running) lfac("DA_CLAN_MEMBERS_INFO", DA_CLAN_MEMBERS_INFO, DEFDELAY, false, ACC_HIGH);
				if (running) Sleep(2000);
				if (running) log("--- clan members greeted !");
				if (running) ez_DA_CLAN_MEMBERS = true;
			} else {
				if (running) log("--- clan members already greeted !");
				if (running) ez_DA_CLAN_MEMBERS = true;
			}
			
			return "cont";
		} else {
			if (!inclan) log("> fam what u doin? join a clan ffs"); else
			if (running) log("> no clan checkin/members stuff ..");
			//if (running) ez_DA_CLAN_CHECKIN = true;
			//if (running) ez_DA_CLAN_MEMBERS = true;
		}
	
		//CLAN donation
		if (ez_DA_CLAN_DONATION && ez_DA_CLAN_SHOP) {
			
		} else
		if (!cb_DA_CLAN_DONATION.isSelected() && !cb_DA_CLAN_SHOP.isSelected()) {
			if (running) log("> no clan stuff ..");
		} else
		if (running && inclan && bbs.exists(DA_CLAN_DONATION.similar(ACC_HIGH)) != null ) {
			if (running) pressButton(DA_CLAN_DONATION, "CLAN DONATIONS");
			if (running) Sleep(2000);
			
			if (!cb_DA_CLAN_DONATION.isSelected() || ez_DA_CLAN_DONATION) {
				ez_DA_CLAN_DONATION = true;
			} else
			if (running && bbs.exists(DA_CLAN_DONATEBTN.similar(ACC_095)) != null) {
				if (running) lfac("DA_CLAN_DONATEBTN", DA_CLAN_DONATEBTN, DEFDELAY, false, ACC_HIGH);
				if (running) Sleep(2000);
				slide(DA_CLAN_DONATE_SLIDER, DA_CLAN_DONATE_PLUS);
				if (running) Sleep(2000);
				if (running) lfac("DA_CLAN_DONATE_SUBMIT1", DA_CLAN_DONATE_SUBMIT1, DEFDELAY, false, ACC_HIGH);
				if (running) Sleep(2000);
				if (running && bbs.exists(DA_CLAN_NE.similar(ACC_HIGH)) != null ) {
					if (running) lfac("DA_CLAN_NEX", DA_CLAN_NEX, DEFDELAY, false, ACCURACY);
				} else
				if (running) lfac("DA_CLAN_DONATE_REWARD", DA_CLAN_DONATE_REWARD, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);

				if (running) lfac("DA_CLAN_DONATEBTN2", DA_CLAN_DONATEBTN2, DEFDELAY, false, ACC_HIGH);
				if (running) Sleep(2000);
				if (running) lfac("DA_CLAN_DONATE_TAB2", DA_CLAN_DONATE_TAB2, DEFDELAY, false, ACC_HIGH);
				if (running) Sleep(2000);
				slide(DA_CLAN_DONATE_SLIDER, DA_CLAN_DONATE_PLUS);
				if (running) Sleep(2000);
				if (running) lfac("DA_CLAN_DONATE_SUBMIT2", DA_CLAN_DONATE_SUBMIT2, DEFDELAY, false, ACC_HIGH);
				if (running) Sleep(2000);
				if (running && bbs.exists(DA_CLAN_NE.similar(ACC_HIGH)) != null ) {
					if (running) lfac("DA_CLAN_NEX", DA_CLAN_NEX, DEFDELAY, false, ACCURACY);
				} else
				if (running) lfac("DA_CLAN_DONATE_REWARD", DA_CLAN_DONATE_REWARD, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);

				if (running) lfac("DA_CLAN_DONATEBTN2", DA_CLAN_DONATEBTN2, DEFDELAY, false, ACC_HIGH);
				if (running) Sleep(2000);
				if (running) lfac("DA_CLAN_DONATE_TAB3", DA_CLAN_DONATE_TAB3, DEFDELAY, false, ACC_HIGH);
				if (running) Sleep(2000);
				slide(DA_CLAN_DONATE_SLIDER, DA_CLAN_DONATE_PLUS);
				if (running) Sleep(2000);
				if (running) lfac("DA_CLAN_DONATE_SUBMIT3", DA_CLAN_DONATE_SUBMIT3, DEFDELAY, false, ACC_HIGH);
				if (running) Sleep(2000);
				if (running && bbs.exists(DA_CLAN_NE.similar(ACC_HIGH)) != null ) {
					if (running) lfac("DA_CLAN_NEX", DA_CLAN_NEX, DEFDELAY, false, ACCURACY);
				} else
				if (running) lfac("DA_CLAN_DONATE_REWARD", DA_CLAN_DONATE_REWARD, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				
				if (running) log("--- clan donations done !");
				if (running) ez_DA_CLAN_DONATION = true;
			} else {
				if (running) log("--- clan donations already done !");
				if (running) ez_DA_CLAN_DONATION = true;
			}
			
			if (!cb_DA_CLAN_SHOP.isSelected() || ez_DA_CLAN_SHOP) {
				ez_DA_CLAN_SHOP = true;
			} else {
				if (running) lfac("DA_CLAN_SHOP", DA_CLAN_SHOP, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				if (running && bbs.exists(DA_CLAN_SHOP_GX.similar(ACC_HIGH)) != null) {
					if (running) lfac("DA_CLAN_SHOP_GX", DA_CLAN_SHOP_GX, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
					if (running) lfac("DA_CLAN_SHOP_GX_BUY", DA_CLAN_SHOP_GX_BUY, DEFDELAY, false, ACCURACY);
					if (running) Sleep(5000);
					if (running && bbs.exists(DA_CLAN_SHOP_GX_BUY_OK) != null) {
						if (running) lfac("DA_CLAN_SHOP_GX_BUY_OK", DA_CLAN_SHOP_GX_BUY_OK, DEFDELAY, false, ACCURACY);
					} else {
						if (running) lfac("DA_CLAN_SHOP_GX_BUY_KO_X", DA_CLAN_SHOP_GX_BUY_KO_X, DEFDELAY, false, ACCURACY);
					}
					if (running) Sleep2(5000);
					if (running) log("--- clan shop buy xp done !");
					if (running) ez_DA_CLAN_SHOP = true;
				} else {
					if (running) log("--- clan shop buy xp already done !");
					if (running) ez_DA_CLAN_SHOP = true;
				}
			}
			
			return "cont";
		} else {
			if (running) log("> no clan donations..");
			//if (running) ez_DA_CLAN_DONATION = true;
			//if (running) ez_DA_CLAN_SHOP = true;
		}
	
		if (ez_DA_ARENA) {
		} else
		if (running && cb_DA_ARENA.isSelected() && bbs.exists(DA_ARENA.similar(ACC_HIGH)) != null) {
			if (running) pressButton(DA_ARENA, "IN THE ARENA");
			if (running) Sleep(2000);
			if (bbs.exists(DA_ARENA_CLAIM_REWARD.similar(ACCURACY)) != null) {
				if (running) lfac("DA_ARENA_CLAIM_REWARD", DA_ARENA_CLAIM_REWARD, DEFDELAY, false, ACCURACY);
				if (running) Sleep(3000);
				if (running) lfac("DA_ARENA_CLAIM_REWARD_OK", DA_ARENA_CLAIM_REWARD_OK, DEFDELAY, false, ACCURACY);
			}

			int battles = 0;
			boolean found = true;
			while (running && battles < 5) {
				found = true;
				if (running) log("> Arena: looking for opponents..");
				if (bbs.exists(DA_ARENA_CP1.similar(ACC_PVP)) != null) {
					if (running) do1v1(DA_ARENA_CP1, (battles+1)+" - CP CAT. 1");
				} else 
				if (bbs.exists(DA_ARENA_CP2.similar(ACC_PVP)) != null) {
					if (running) do1v1(DA_ARENA_CP2, (battles+1)+" - CP CAT. 2");
				} else 
				if (bbs.exists(DA_ARENA_CP3.similar(ACC_PVP)) != null) {
					if (running) do1v1(DA_ARENA_CP3, (battles+1)+" - CP CAT. 3");
				} else 
				if (bbs.exists(DA_ARENA_CP4.similar(ACC_PVP)) != null) {
					if (running) do1v1(DA_ARENA_CP4, (battles+1)+" - CP CAT. 4");
				} else 
				if (bbs.exists(DA_ARENA_CP5.similar(ACC_PVP)) != null) {
					if (running) do1v1(DA_ARENA_CP5, (battles+1)+" - CP CAT. 5");
				} else {
					found = false;
					if (running) log("> no suitable matches found. refreshing..");
					if (running) lfac("DA_ARENA_REFRESH", DA_ARENA_REFRESH, DEFDELAY, false, ACCURACY);
				}
				if (found) {
					boolean ok = false;
					while (running && !ok) {
						if (running && bbs.exists(DA_ARENA_RANK_INCREASED.similar(ACCURACY)) != null) {
							if (running) lfac("DA_ARENA_RANK_INCREASED", DA_ARENA_RANK_INCREASED, DEFDELAY, false, ACCURACY);
						}
						if (running && bbs.exists(DA_ARENA_OK.similar(ACCURACY)) != null) {
							if (running) lfac("DA_ARENA_OK", DA_ARENA_OK, 300, false, ACCURACY);
							ok = true;
						}
						if (running) Sleep2(5000);
					}
					battles++;
				}
				if (running) Sleep(2000);
			}
			if (running) log("> arena fights complete..");
			if (running) ez_DA_ARENA = true;
			return "cont";
		} else {
			if (running) log("> no arena ..");
			//if (running) ez_DA_ARENA = true;
		}
		
		if (ez_DA_ELITE_DUNGEON) {
		} else
		if (running && cb_DA_ELITE_DUNGEON.isSelected() && bbs.exists(DA_ELITE_DUNGEON.similar(ACC_HIGH)) != null) {
			if (running) pressButton(DA_ELITE_DUNGEON, "ELITE DUNGEON");
			if (running) Sleep(3000);
			
			if (running) slidec(400, 900, 400, 400);
			if (running) Sleep(3000);

			int maxctr = 0;
			boolean notfound = true;
			while (running && maxctr < 8 && notfound) {
				if (bbs.exists(DA_ELITE_DUNGEON_CURRENT_BTN.similar(ACC_099)) != null) notfound = false; else {
					//score(DA_ELITE_DUNGEON_CURRENT_BTN);
					if (maxctr == 1 || maxctr == 3 || maxctr == 5) {
						if (running) slidec(400, 900, 400, 300);
					} 
					if (running) Sleep(3000);
				}
				maxctr++;
			}

			if (maxctr == 8) {
				log("> could not enter elite dungeon");
				return "cont";
			}
			
			if (running) lfac("DA_ELITE_DUNGEON_CURRENT_BTN", DA_ELITE_DUNGEON_CURRENT_BTN, DEFDELAY, false, ACC_099);
			if (running) Sleep(2000);
			
			if (running) lfac("DA_ELITE_DUNGEON_AUTOCLEAR", DA_ELITE_DUNGEON_AUTOCLEAR, DEFDELAY, false, ACCURACY);
			if (running) Sleep(2000);
			if (running && bbs.exists(DA_ELITE_NOT_ENOUGH_DIAMONDS.similar(ACCURACY)) != null) {
				if (running) log("> Not enough diamonds to autocomplete, will skip ..");
				if (running) lfac("DA_ELITE_NOT_ENOUGH_DIAMONDS_X", DA_ELITE_NOT_ENOUGH_DIAMONDS_X, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
			} else {
				if (running) lfac("DA_ELITE_DUNGEON_AUTOCLEAR_OK", DA_ELITE_DUNGEON_AUTOCLEAR_OK, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				if (running) log("> elite dungeon done..");
			}
			if (running) ez_DA_ELITE_DUNGEON = true;
			return "cont";
		} else {
			if (running) log("> no elite dungeon ..");
			//if (running) ez_DA_ELITE_DUNGEON = true;
		}
		
		if (ez_DA_HERBS) {
		} else
		if (running && cb_DA_HERBS.isSelected() && bbs.exists(DA_HERBS.similar(ACC_HIGH)) != null) {
			if (running) pressButton(DA_HERBS, "HERBS");
			if (running) Sleep(6000);
			if (running) lfac("DA_HERBS_ENTER", DA_HERBS_ENTER, 20, false, ACCURACY);
			if (running) Sleep2(DEF_STAGE_PAUSE);
			if (running) goClick(BTN_MAP_X, BTN_MAP_Y); //map
			if (running) Sleep(3000);
			if (running) goClick(Integer.parseInt(coordHerbs1X.getText()), Integer.parseInt(coordHerbs1Y.getText())); //go here to gather
			if (running) Sleep(3000);
			if (running) lfac("DA_HERBS_MAP_X", DA_MAP_X, DEFDELAY, false, ACCURACY);
			if (running) Sleep(3000);
			if (running && cb_HERBS_EINHASADS.isSelected()) {
				if (running) goClick(BTN_POTIONS_X, BTN_POTIONS_Y); //open potions
				if (running) Sleep(3000);
				if (running) goClick(GATHERPOTIONX, GATHERPOTIONY); //use gather potion
				if (running) Sleep(3000);
				if (running) goClick(BTN_POTIONS_X, BTN_POTIONS_Y); //close potions
				if (running) Sleep2(8000);
			} else {
				if (running) Sleep2(DEF_STAGE_PAUSE);
			}
			boolean notdone = true;
			int deaths = 0;
			while (running && notdone) {
				if (running) goClick(BTN_AUTO_X, BTN_AUTO_Y); //auto
				if (running) Sleep(2000);
				if (running) goClick(GATHERBTNX, GATHERBTNY); //use gather potion

				if (running && (bbs.exists(MQ_SPOT_REVIVAL.similar(ACCURACY)) != null || bbs.exists(MQ_DEATH_X.similar(ACCURACY)) != null)) {
					deaths++;
					if (running) log("! died at herbs #"+deaths);
					if (running && bbs.exists(MQ_SPOT_REVIVAL.similar(ACCURACY)) != null) {
						if (running) lfac("MQ_SPOT_REVIVAL", MQ_SPOT_REVIVAL, DEFDELAY, false, ACCURACY);
						if (running) Sleep(3000);
						if (running) goClick(1840, 430); //X
						if (running) Sleep(2000);
					} else {
						if (running) lfac("MQ_DEATH_X", MQ_DEATH_X, DEFDELAY, false, ACCURACY);
						if (running) Sleep(2000);
					}

					if (running) goClick(BTN_MAP_X, BTN_MAP_Y); //map
					if (running) Sleep(3000);
					if (running && deaths == 1) goClick(Integer.parseInt(coordHerbs2X.getText()), Integer.parseInt(coordHerbs2Y.getText())); //go here to gather because area 1 is hostile
					if (running && deaths == 2) goClick(Integer.parseInt(coordHerbs3X.getText()), Integer.parseInt(coordHerbs3Y.getText())); //go here to gather because area 2 is hostile
					if (running && deaths == 3) {
						if (running) log("> PK 3 times.. too crowded..");
						notdone = false;
					}
					if (running) Sleep(2000);
					if (running) lfac("DA_HERBS_MAP_X", DA_MAP_X, DEFDELAY, false, ACCURACY);
				}
				if (running && bbs.exists(DA_HERBS_COMPLETE.similar(ACCURACY)) != null) {
					if (running) log("> Done with herbs.");
					notdone = false;
				}
			}
			if (running) goClick(BTN_AUTO_X, BTN_AUTO_Y); //auto
			if (running) Sleep(500);
			boolean exited = false;
			while (running && !exited) {
				if (running) goClick(BTN_LEAVE_DUNGEON_X, BTN_LEAVE_DUNGEON_Y); //exit dungeon
				if (running) Sleep(2000);
				if (running && bbs.exists(DA_DUNGEON_EXIT_CONF.similar(ACCURACY)) != null) {
					if (running) log("> Exiting dungeon ..");
					if (running) lfac("DA_HERBS_EXIT_CONF", DA_DUNGEON_EXIT_CONF, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
					if (bbs.exists(DA_DUNGEON_OK.similar(ACCURACY)) != null) {
						if (running) lfac("DA_DUNGEON_CANT_LEAVE_OK", DA_DUNGEON_OK, 3, false, ACCURACY);
						if (running) Sleep(2000);
					} else exited = true;
				}
			}
			if (running) Sleep2(DEF_STAGE_PAUSE);
			if (running) lfac("EXIT_FROM_MENU", EXIT_FROM_MENU, DEFDELAY, false, ACCURACY);
			if (running) Sleep(2000);
			if (running) goClick(200, 500);
			if (running) log("> herbs done..");
			if (running) ez_DA_HERBS = true;
			return "cont";
		} else {
			if (running) log("> no herbs ..");
			//if (running) ez_DA_HERBS = true;
		}
		
		if (ez_DA_FREE_HG) {
		} else
		if (running && cb_DA_FREE_HG.isSelected() && bbs.exists(DA_FREE_HG.similar(ACC_HIGH)) != null) { 
			if (running) pressButton(DA_FREE_HG, "FREE HG DRAW");
			if (running) Sleep(4000);
			if (running && bbs.exists(DA_FREE_HG_BOX.similar(ACCURACY)) != null) {
				if (running) lfac("DA_FREE_HG_BOX", DA_FREE_HG_BOX, DEFDELAY, false, ACCURACY);
				if (running) Sleep2(5000);
				if (running) lfac("DA_FREE_HG_BOX_OPEN", DA_FREE_HG_BOX_OPEN, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				if (running) lfac("DA_FREE_HG_BOX_OPEN_OK", DA_FREE_HG_BOX_OPEN_OK, DEFDELAY, false, ACCURACY);
				if (running) log("> free HG draw done..");
			} else if (running) log("> free HG draw not available..");
			if (running) ez_DA_FREE_HG = true;
			return "cont";
		} else {
			if (running) log("> no free HG draw ..");
			//if (running) ez_DA_FREE_HG = true;
		}
		
		if (ez_DA_TEMPLE) {
		} else
		if (running && cb_DA_TEMPLE.isSelected() && bbs.exists(DA_TEMPLE.similar(ACC_HIGH)) != null) {
			if (running) pressButton(DA_TEMPLE, "TEMPLE GUARDIAN");
			if (running) Sleep(3000);
			for (int i=0;i<2;i++) { //run two times
				if (running) log(">temple guardian run "+(i+1)+"..");
				if (running) lfac("DA_TEMPLE_PARTY", DA_TEMPLE_PARTY, DEFDELAY, false, ACCURACY);
				if (running) Sleep(3000);
				if (running && bbs.exists(DA_TEMPLE_INPARTY.similar(ACCURACY)) != null) {
					if (running) lfac("DA_TEMPLE_INPARTY_OK", DA_TEMPLE_INPARTY_OK, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
					if (running) lfac("EXIT", EXIT, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
					if (running) log("> must leave party ..");
					if (running) leaveParty();
					return "cont"; //this will trigger retry
				}
				if (running) lfac("DA_TEMPLE_OK", DA_TEMPLE_OK, 1000, false, ACCURACY); //10 min wait for dungeon complete
				if (running) Sleep2(DEF_STAGE_PAUSE);
			}
			if (running) log("> temple guardian done..");
			if (running) ez_DA_TEMPLE = true;
			return "cont";
		} else {
			if (running) log("> no temple guardian ..");
			//if (running) ez_DA_TEMPLE = true;
		}
		
		if (ez_DA_VAULT) {
		} else
		if (running && cb_DA_VAULT.isSelected() && bbs.exists(DA_VAULT.similar(ACC_HIGH)) != null) {
			if (running) pressButton(DA_VAULT, "ADENA VAULT");
			if (running) Sleep(3000);
			if (running) lfac("DA_VAULT_AC", DA_VAULT_AC, DEFDELAY, false, ACCURACY);
			if (running) Sleep(2000);
			boolean failedv = false;
			if (running && bbs.exists(DA_VAULT_AC_GO.similar(ACC_HIGH)) != null) {
				if (running) lfac("DA_VAULT_AC_GO", DA_VAULT_AC_GO, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				if (running && bbs.exists(DA_TRIALS_DENIED.similar(ACC_HIGH)) != null) {
					if (running) lfac("DA_TRIALS_OK", DA_TRIALS_OK, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
					failedv = true;
				} else {
					if (running) lfac("DA_VAULT_AC_OK", DA_VAULT_AC_OK, DEFDELAY, false, ACCURACY);
				}
				if (running) Sleep(2000);
			} else failedv = true;
			
			if (failedv) {
				if (running) lfac("DA_VAULT_AC_BACK", DA_VAULT_AC_BACK, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				if (running) lfac("DA_VAULT_ENTER", DA_VAULT_ENTER, DEFDELAY, false, ACCURACY);
				if (running && bbs.exists(DA_VAULT_CONFIRM) != null) {
					if (running) Sleep(2000);
					if (running) lfac("DA_VAULT_CONFIRM", DA_VAULT_CONFIRM, DEFDELAY, false, ACCURACY); //same image as trials
				}
				if (running) lfac("DA_VAULT_FINISHED_OK", DA_VAULT_FINISHED_OK, 300, false, ACCURACY);
				if (running) Sleep2(5000);
			}
			if (running) log("> adena vault done..");
			if (running) ez_DA_VAULT = true;
			return "cont";
		} else {
			if (running) log("> no adena vault ..");
			//if (running) ez_DA_VAULT = true;
		}
		
		if (ez_DA_TRIALS) {
		} else
		if (running && cb_DA_TRIALS.isSelected() && bbs.exists(DA_TRIALS.similar(ACC_HIGH)) != null) {
			if (running) pressButton(DA_TRIALS, "TRIALS OF EXPERIENCE");
			if (running) Sleep(3000);

			for (int i=0;i<2;i++) { //run two times
				if (running) lfac("DA_TRIALS_AC", DA_TRIALS_AC, DEFDELAY, false, ACCURACY);
				if (running) Sleep(3000);
				boolean failedt = false;
				if (running && bbs.exists(DA_TRIALS_AC_GO.similar(ACC_HIGH)) != null) {
					if (running) lfac("DA_TRIALS_AC_GO", DA_TRIALS_AC_GO, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
					if (running && bbs.exists(DA_TRIALS_DENIED.similar(ACC_HIGH)) != null) {
						if (running) lfac("DA_TRIALS_OK", DA_TRIALS_OK, DEFDELAY, false, ACCURACY);
						if (running) Sleep(2000);
						failedt = true;
					} else {
						if (running) lfac("DA_TRIALS_AC_OK", DA_TRIALS_AC_OK, DEFDELAY, false, ACCURACY);
					}
					if (running) Sleep(2000);
				} else {
					failedt = true;
				}
				
				if (failedt) {
					if (running) lfac("DA_TRIALS_AC_BACK", DA_TRIALS_AC_BACK, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
					if (running) lfac("DA_TRIALS_ENTER", DA_TRIALS_ENTER, DEFDELAY, false, ACCURACY);
					if (running && bbs.exists(DA_TRIALS_CONFIRM) != null) {
						if (running) Sleep(2000);
						if (running) lfac("DA_TRIALS_CONFIRM", DA_TRIALS_CONFIRM, DEFDELAY, false, ACCURACY);
					}
					if (running) lfac("DA_TRIALS_FINISHED_OK", DA_TRIALS_FINISHED_OK, 300, false, ACCURACY);
					if (running) Sleep2(5000);
				}
			}
			if (running) log("> trials of experience done..");
			if (running) ez_DA_TRIALS = true;
			return "cont";
		} else {
			if (running) log("> no trials of experience ..");
			//if (running) ez_DA_TRIALS = true;
		}
		
		if (ez_DA_CIRCLE) {
		} else
		if (running && cb_DA_CIRCLE.isSelected() && bbs.exists(DA_CIRCLE.similar(ACC_HIGH)) != null) {
			if (running) pressButton(DA_CIRCLE, "SUMMONING CIRCLE");
			if (running) Sleep(3000);
			for (int i=0;i<2;i++) { //run two times
				if (running) log(">summoning circle run "+(i+1)+"..");
				if (running) lfac("DA_CIRCLE_PARTY", DA_CIRCLE_PARTY, DEFDELAY, false, ACCURACY);
				if (running) Sleep(3000);
				if (running && bbs.exists(DA_CIRCLE_INPARTY.similar(ACCURACY)) != null) {
					if (running) lfac("DA_CIRCLE_INPARTY_OK", DA_CIRCLE_INPARTY_OK, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
					if (running) lfac("EXIT", EXIT, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
					if (running) log("> must leave party ..");
					if (running) leaveParty();
					return "cont"; //this will trigger retry
				}
				if (running) lfac("DA_CIRCLE_OK", DA_CIRCLE_OK, 1000, false, ACCURACY); //10 min wait for dungeon complete
				if (running) Sleep2(DEF_STAGE_PAUSE);
			}
			if (running) log("> summoning circle done..");
			if (running) ez_DA_CIRCLE = true;
			return "cont";
		} else {
			if (running) log("> no summoning circle ..");
			//if (running) ez_DA_CIRCLE = true;
		}
		return "";
		
	}
	
	
	public static void doTheDailies(){
		log("--- Starting DA Mode..");
		start();
		resetDA();
		done_da = false;
		

		Thread t = new Thread(() -> {
			
			int loopctr = 0;
			boolean firsttry = true;
			while (running) { // loop this event
				
				loopctr++;
				logsl("=== DA Event loop: "+loopctr+" ===\n");
				
				if (running && !firsttry) {
					dailyTimer = 19;
					checkOnDaily();
				} else {
					firsttry = false;
				}
				
				if (bbs.exists(EXIT) != null) {
					if (running) lfac("EXIT", EXIT, 2, false, ACCURACY);
					if (running) Sleep(2000);
				}
				
				while (running && bbs.exists(DA_SCREEN.similar(ACCURACY)) == null) {
					exitMenues();
					if (running) lfac("DAILY_ACT", DAILY_ACT, DEFDELAY, false, ACCURACY);
					if (running) Sleep(3000);
				}
				
				while (running && bbs.exists(DA_CLAIM_REWARD.similar(ACC_HIGH)) != null) {
					if (running) lfac("DA_CLAIM_REWARD", DA_CLAIM_REWARD, DEFDELAY, false, ACCURACY);
					if (running) Sleep(3000);
				}
				
				if (running && bbs.exists(DA_COMPLETE.similar(ACC_HIGH)) != null) {
					if (running) log("> it appears DA is done..");
				} else {
					if (running && dailies() == "cont") continue;
					if (running) log("> lets drag ..");
					if (running) slidec(400, 700, 400, 360);
					if (running) Sleep(3000);
					
					if (running && bbs.exists(DA_COMPLETE.similar(ACC_HIGH)) != null) {
						if (running) log("> it appears DA is done..");
					} else { 
						if (running && dailies() == "cont") continue;
						if (running) log("> lets drag ..");
						if (running) slidec(400, 700, 400, 360);
						if (running) Sleep(3000);
						
						if (running && bbs.exists(DA_COMPLETE.similar(ACC_HIGH)) != null) {
							if (running) log("> it appears DA is done..");
						} else { 
							if (running && dailies() == "cont") continue;
						}
					}
				}
				
				///////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				if (running && bbs.exists(DA_REW1.similar(ACC_095)) != null) {
					if (running) lfac("DA_REW1", DA_REW1, DEFDELAY, false, ACC_095);
					if (running) Sleep(3000);
					if (running) log("> got coin reward ..");
				} 

				if (running && bbs.exists(DA_REW2.similar(ACC_095)) != null) {
					if (running) lfac("DA_REW2", DA_REW2, DEFDELAY, false, ACC_095);
					if (running) Sleep(3000);
					if (running) log("> got gems reward ..");
				}

				if (running && bbs.exists(DA_REW3.similar(ACC_095)) != null) {
					if (running) lfac("DA_REW3", DA_REW3, DEFDELAY, false, ACC_095);
					if (running) Sleep(3000);
					if (running) log("> got sp reward ..");
				}
				
				if (running) {
					running = false;
				}

				log("--- DAILY ACTIVITIES FINISHED.");
				done_da = true;
				jobDone();
				busy = false;

			}
			
			
		});
		t.start();
		
	}
	
	public static void doItAll(){
	
		log("--- Starting FULL AUTO Mode..");
		
		auto = true;

		done_da = false;
		done_we = false;
		done_sq = false;
		done_ms = false;
		done_cl = false;
		done_ch = false;
		done_rm = false;
		done_in = false;
		done_dr = false;
		
		done_dailies = false;
		
		Thread t = new Thread(() -> {

			while (auto) { // loop this event
			
				if (!busy) {
					Sleep2(5000);
					if (!done_da && auto && cb_DAILIES.isSelected() ) 		{ autosequence = 1; doTheDailies(); } else
					if (!done_we && auto && cb_WEEKLY.isSelected() ) 		{ autosequence = 2; weeklyMode(); } else
					if (!done_sq && auto && cb_SUBQUEST.isSelected() ) 		{ autosequence = 3; subquestsMode(); } else
					if (!done_ms && auto && cb_MAIN_QUEST.isSelected() ) 	{ autosequence = 4; storyMode(); } else
					if (!done_cl && auto && cb_CH_STUFF.isSelected() ) 		{ autosequence = 5; clanHall(); } else
					if (!done_ch && auto && cb_CHALLENGES.isSelected() ) 	{ autosequence = 6; challenges(); } else
					if (!done_rm && auto && cb_SORT_MAIL.isSelected() ) 	{ autosequence = 7; readMail(); } else
					if (!done_in && auto && cb_INV_SORT.isSelected() ) 		{ autosequence = 8; sortInv(); } else
					if (!done_dr && auto && cb_DUNGEON_RUN.isSelected() ) 	{ autosequence = 9; dungeonInit = true; dungeonRun(); } else
																			{ autosequence = 0; auto = false; }
				}
				Sleep2(5000);
			}
			
			log("[[[ FULL AUTO MODE DONE ! ]]]");
			
			btn_AUTO.setEnabled(true);

			auto = false;
		});
		t.start();
		
	}

	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void clanHall() {

		log("--- ClanHall Mode..");
		start();
		done_cl = false;
		
		Thread t = new Thread(() -> {
			
			exitMenues();
			dailyTimer = 19;
			if (running) checkOnDaily();
			
			if (running) goClick(BTN_MNU_X, BTN_MNU_Y); //menu
			if (running) Sleep(3000);
			if (running && bbs.exists(BTN_CLAN.similar(ACCURACY)) != null) {
				if (running) lfac("BTN_CLAN", BTN_CLAN, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
			} else {
				exitMenues();
				if (running) goClick(BTN_MNU_X, BTN_MNU_Y); //menu
				if (running) Sleep(3000);
				if (running) lfac("BTN_CLAN", BTN_CLAN, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
			}
			
			if (running) lfac("BTN_CLAN_HALL", BTN_CLAN_HALL, DEFDELAY, false, ACCURACY);
			if (running) Sleep2(DEF_STAGE_PAUSE);

			if (running) goClick(BTN_CH_MERCHANT_X, BTN_CH_MERCHANT_Y); //ch merchant gift
			if (running) Sleep(3000);
			if (running && bbs.exists(CH_MERCH_GIFT.similar(ACCURACY)) != null) {
				if (running) lfac("CH_MERCH_GIFT", CH_MERCH_GIFT, DEFDELAY, false, ACCURACY);
				if (running) Sleep2(8000);
			}
			if (running) lfac("EXIT", EXIT, DEFDELAY, false, ACCURACY);
			if (running) Sleep(2000);
			
			if (running) goClick(BTN_CH_MENU_X, BTN_CH_MENU_Y); //ch menu
			if (running) Sleep(2000);
			if (running) goClick(BTN_CH_RELIC_X, BTN_CH_RELIC_Y); //ch relic
			if (running) Sleep(2000);
			while (running && bbs.exists(CH_RELIC_CHECK.similar(ACC_HIGH)) != null) {
				if (running) lfac("CH_RELIC_CHECK", CH_RELIC_CHECK, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				if (running) lfac("CH_RELIC_CHECK_OK", CH_RELIC_CHECK_OK, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
			}
			boolean reg = true;
			if (running && bbs.exists(CH_RELIC_BOX2.similar(ACC_HIGH)) != null) {
				if (running) lfac("CH_RELIC_BOX2", CH_RELIC_BOX2, DEFDELAY, false, ACC_HIGH);
				if (running) Sleep(2000);
				if (running && bbs.exists(CH_RELIC_REGISTER.similar(ACC_HIGH)) != null) {
					if (running) lfac("CH_RELIC_REGISTER", CH_RELIC_REGISTER, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
				} else reg = false;
			}
			if (running && bbs.exists(CH_RELIC_BOX3.similar(ACC_HIGH)) != null) {
				if (running) lfac("CH_RELIC_BOX3", CH_RELIC_BOX3, DEFDELAY, false, ACC_HIGH);
				if (running) Sleep(2000);
				if (running && bbs.exists(CH_RELIC_REGISTER.similar(ACC_HIGH)) != null) {
					if (running) lfac("CH_RELIC_REGISTER", CH_RELIC_REGISTER, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
				} else reg = false;
			}
			if (running && reg && bbs.exists(CH_RELIC_BOX1.similar(ACCURACY)) != null) {
				if (running) lfac("CH_RELIC_BOX1", CH_RELIC_BOX1, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				if (running && bbs.exists(CH_RELIC_REGISTER.similar(ACC_HIGH)) != null) {
					if (running) lfac("CH_RELIC_REGISTER", CH_RELIC_REGISTER, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
				} else reg = false;
			} else reg = false;
			
			if (running && reg && bbs.exists(CH_RELIC_BOX1.similar(ACCURACY)) != null) {
				if (running) lfac("CH_RELIC_BOX1", CH_RELIC_BOX1, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				if (running && bbs.exists(CH_RELIC_REGISTER.similar(ACC_HIGH)) != null) {
					if (running) lfac("CH_RELIC_REGISTER", CH_RELIC_REGISTER, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
				} else reg = false;	
			} else reg = false;
			
			if (running && reg && bbs.exists(CH_RELIC_BOX1.similar(ACCURACY)) != null) {
				if (running) lfac("CH_RELIC_BOX1", CH_RELIC_BOX1, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				if (running && bbs.exists(CH_RELIC_REGISTER.similar(ACC_HIGH)) != null) {
					if (running) lfac("CH_RELIC_REGISTER", CH_RELIC_REGISTER, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
				}
			}
			
			if (running) lfac("EXIT", EXIT, DEFDELAY, false, ACCURACY);
			if (running) Sleep(3000);
			if (running) goClick(BTN_LEAVE_DUNGEON_X, BTN_LEAVE_DUNGEON_Y); //exit dungeon
			if (running) Sleep2(DEF_STAGE_PAUSE);
			
			log("--- ClanHall Mode Done..");
			done_cl = true;
			jobDone();
			busy = false;

			
		});
		t.start();
		
	}
	
	public static void challenges() {

		log("--- Challenges Mode..");
		start();
		done_ch = false;
		
		Thread t = new Thread(() -> {
			
			exitMenues();
			
			dailyTimer = 19;
			checkOnDaily();
			
			if (running) goClick(BTN_MNU_X, BTN_MNU_Y); //menu
			if (running) Sleep(3000);
			
			if (running && bbs.exists(BTN_CHALLENGES.similar(ACCURACY)) != null) {
				if (running) lfac("BTN_CHALLENGES", BTN_CHALLENGES, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
			} else {
				exitMenues();
				if (running) goClick(BTN_MNU_X, BTN_MNU_Y); //menu
				if (running) Sleep(3000);
				if (running) lfac("BTN_CHALLENGES", BTN_CHALLENGES, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
			}
			
			log("--- Challenges Mode - Achievements");
			
			if (running) lfac("BTN_CHIEVES", BTN_CHIEVES, DEFDELAY, false, ACCURACY);
			if (running) Sleep(3000);
			
			if (running && bbs.exists(CHA_CLAIM.similar(ACC_095)) != null) {
				if (running) lfac("CHA_CLAIM", CHA_CLAIM, DEFDELAY, false, ACC_095);
				if (running) Sleep(3000);
			}
			if (running && bbs.exists(CHA_OK.similar(ACCURACY)) != null) {
				if (running) lfac("CHA_OK", CHA_OK, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
			}
			
			log("--- Challenges Mode - please wait..");
			
			while (running && bbs.exists(CHA_CLAIM_SMALL.similar(ACC_MAX)) != null) {
				if (running) lfac("CHA_CLAIM_SMALL", CHA_CLAIM_SMALL, DEFDELAY, false, ACC_MAX);
				if (running) Sleep(3000);
				if (running && bbs.exists(CHA_OK.similar(ACCURACY)) != null) {
					if (running) lfac("CHA_OK", CHA_OK, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
				}
			}
			if (running && bbs.exists(CHA_RED_DOT.similar(ACCURACY)) != null) {
				for (int i=0; i<4; i++) {
					if (running && bbs.exists(CHA_RED_DOT.similar(ACCURACY)) != null) {
						if (running) lfac("CHA_RED_DOT", CHA_RED_DOT, DEFDELAY, false, ACCURACY);
						if (running) Sleep(2000);
						while (running && bbs.exists(CHA_CLAIM_SMALL.similar(ACC_MAX)) != null) {
							if (running) lfac("CHA_CLAIM_SMALL", CHA_CLAIM_SMALL, DEFDELAY, false, ACC_MAX);
							if (running) Sleep(3000);
							if (running && bbs.exists(CHA_OK.similar(ACCURACY)) != null) {
								if (running) lfac("CHA_OK", CHA_OK, DEFDELAY, false, ACCURACY);
								if (running) Sleep(2000);
							}
						}
					}
				}
			}
			
			if (running) Sleep(2000);
			
			if (running) lfac("GO_BACK", GO_BACK, DEFDELAY, false, ACCURACY);
			if (running) Sleep(2000);

			log("--- Challenges Mode - Codex");
			
			if (running) lfac("BTN_CODEX", BTN_CODEX, DEFDELAY, false, ACCURACY);
			if (running) Sleep(3000);
			
			if (running && bbs.exists(CHA_CODEX_LISTALL.similar(ACC_095)) != null) {
				if (running) lfac("CHA_CODEX_LISTALL", CHA_CODEX_LISTALL, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				if (running) lfac("CHA_CODEX_OK", CHA_CODEX_OK, DEFDELAY, false, ACCURACY);
				if (running) Sleep(3000);
				
				boolean okneeded = true;
				while (okneeded) {
					if (running && bbs.exists(CHA_CODEX_REG_OK.similar(ACCURACY)) != null) {
						if (running) lfac("CODEX_OK", CHA_CODEX_REG_OK, DEFDELAY, false, ACCURACY);
						if (running) Sleep(2000);
					} else okneeded = false;
				}
			}
			if (running) lfac("EXIT", EXIT, DEFDELAY, false, ACCURACY);
			if (running) Sleep(3000);
			
			log("--- Challenges Mode Done..");
			done_ch = true;
			jobDone();

			busy = false;

		});
		t.start();
		
	}
	
	public static void readMail() {

		log("--- ReadMail Mode..");
		start();
		done_rm = false;

		Thread t = new Thread(() -> {
			

			exitMenues();
			
			dailyTimer = 19;
			checkOnDaily();
			
			if (running) goClick(BTN_MAIL_X, BTN_MAIL_Y); //mail
			if (running) Sleep(3000);
			
			if (running && bbs.exists(MAIL_SCREEN.similar(ACCURACY)) != null) {
			} else {
				exitMenues();
				if (running) goClick(BTN_MAIL_X, BTN_MAIL_Y); //mail
				if (running) Sleep(3000);
			}
			
			if (running && bbs.exists(MAIL_RED_DOT_MAIN.similar(ACC_HIGH)) != null) {
				if (running) lfac("MAIL_COLLECT_ALL", MAIL_COLLECT_ALL, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				while (running && bbs.exists(MAIL_NEXT.similar(ACC_HIGH)) != null) {
					if (running) lfac("MAIL_NEXT", MAIL_NEXT, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
				}
				if (running) lfac("MAIL_OK", MAIL_OK, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
			}
			
			while (running && bbs.exists(MAIL_RED_DOT.similar(ACC_HIGH)) != null) {
				if (running) lfac("MAIL_RED_DOT", MAIL_RED_DOT, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				if (running) lfac("MAIL_COLLECT_ALL", MAIL_COLLECT_ALL, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				while (running && bbs.exists(MAIL_NEXT.similar(ACC_HIGH)) != null) {
					if (running) lfac("MAIL_NEXT", MAIL_NEXT, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
				}
				if (running) lfac("MAIL_OK", MAIL_OK, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
			}
			
			if (running) lfac("EXIT", EXIT, DEFDELAY, false, ACCURACY);
			if (running) Sleep(3000);
			
			log("--- ReadMail Mode Done..");
			done_rm = true;
			jobDone();
			busy = false;

		});
		t.start();
		
	}
	

	public static void sortInv(){

		log("--- SortInv Mode..");
		start();
		done_in = false;

		Thread t = new Thread(() -> {
			
			exitMenues();
			
			dailyTimer = 19;
			checkOnDaily();
			
			if (running) goClick(BTN_INV_X, BTN_INV_Y); //inventory
			if (running) Sleep(3000);
			
			if (running && bbs.exists(INV_SCREEN.similar(ACCURACY)) != null) {
			} else {
				exitMenues();
				if (running) goClick(BTN_INV_X, BTN_INV_Y); //inventory
				if (running) Sleep(3000);
			}
			
			
			if (running && bbs.exists(INV_POTION.similar(ACCURACY)) != null) {
				if (running) lfac("INV_POTION", INV_POTION, DEFDELAY, false, ACCURACY);
			} else {
				log("--- No items found..");
				done_in = true;
				jobDone();
			}
			
			if (running) Sleep(2000);

			boolean done=false;
			int fails = 0;
			while (running && !done) { // loop this event
				
				if (running && (bbs.exists(INV_RED_DOT.similar(ACC_095-0.01f)) != null || bbs.exists(INV_RED_DOT_NEW.similar(ACC_095-0.01f)) != null || bbs.exists(INV_S1_USE.similar(ACCURACY)) != null)) {
				
					if (running && bbs.exists(INV_RED_DOT.similar(ACC_095-0.01f)) != null) {
						lfac("INV_RED_DOT", INV_RED_DOT, DEFDELAY, false, ACC_095-0.01f);
						Sleep(700);
					}
					
					if (running && bbs.exists(INV_RED_DOT_NEW.similar(ACC_095-0.01f)) != null) {
						lfac("INV_RED_DOT_NEW", INV_RED_DOT_NEW, DEFDELAY, false, ACC_095-0.01f);
						Sleep(700);
					}
					
					if (running) log("--- SortInv Mode - found item..");
					
					//log("S1");
					//STAGE 1 ////////////////////////////////////////////////////////////////////////////////////////////////
					if (running && bbs.exists(INV_S1_USE.similar(ACCURACY)) != null) {
						if (running) lfac("INV_S1_USE", INV_S1_USE, 1, false, ACCURACY);
						Sleep(700);
					}
					if (running && bbs.exists(INV_S4_OK.similar(ACC_HIGH)) != null) {
						if (running) lfac("INV_S4_OK", INV_S4_OK, 1, false, ACC_HIGH);
						Sleep(700);
						continue;
					}
					
					if (running) log("--- SortInv Mode.");

					//log("S2");
					//STAGE 2////////////////////////////////////////////////////////////////////////////////////////////////
					if (running && bbs.exists(INV_S2_USE.similar(ACC_HIGH)) != null) {
						if (running) lfac("INV_S2_USE", INV_S2_USE, 1, false, ACC_HIGH);
						Sleep(700);
						if (running && bbs.exists(INV_S4_OK.similar(ACC_095)) != null) {
							if (running) lfac("INV_S4_OK", INV_S4_OK, 1, false, ACC_095);
							Sleep(700);
							continue;
						}
					}
					
					if (running) log("--- SortInv Mode..");
					
					boolean nexted = false;
					while (running && bbs.exists(INV_S2_NEXT.similar(ACC_HIGH)) != null) {
						if (running) lfac("INV_S2_NEXT", INV_S2_NEXT, 1, false, ACC_HIGH);
						Sleep(700);
						nexted = true;
					}
					
					if (nexted) {
						if (running) lfac("INV_S4_OK", INV_S4_OK, 1, false, ACC_HIGH);
						Sleep(700);
					} else {
						if (running) log("--- SortInv Mode - computing..");
						if (running && bbs.exists(INV_S2_CHOICE_TICKET.similar(ACC_HIGH)) != null) {
							if (running) lfac("INV_S2_CHOICE_TICKET", INV_S2_CHOICE_TICKET, 1, false, ACC_HIGH);
							Sleep(700);
							if (running) goClick(300, 300); //bugfix
							Sleep(300);
						} else
						if (running && bbs.exists(INV_S2_CHOICE_STONE.similar(ACC_HIGH)) != null) {
							if (running) lfac("INV_S2_CHOICE_STONE", INV_S2_CHOICE_STONE, 1, false, ACC_HIGH);
							Sleep(700);
							if (running) goClick(300, 300); //bugfix
							Sleep(300);
						} else
						if (running && bbs.exists(INV_S2_CHOICE_STONE2.similar(ACC_HIGH)) != null) {
							if (running) lfac("INV_S2_CHOICE_STONE2", INV_S2_CHOICE_STONE2, 1, false, ACC_HIGH);
							Sleep(700);
							if (running) goClick(300, 300); //bugfix
							Sleep(300);
						} else
						if (running && bbs.exists(INV_S2_CHOICE_VARNISH.similar(ACC_HIGH)) != null) {
							if (running) lfac("INV_S2_CHOICE_VARNISH", INV_S2_CHOICE_VARNISH, 1, false, ACC_HIGH);
							Sleep(700);
							if (running) goClick(300, 300); //bugfix
							Sleep(300);
						} else
						if (running && bbs.exists(INV_S2_CHOICE_UPGRADE.similar(ACC_HIGH)) != null) {
							if (running) lfac("INV_S2_CHOICE_UPGRADE", INV_S2_CHOICE_UPGRADE, 1, false, ACC_HIGH);
							Sleep(700);
							if (running) goClick(300, 300); //bugfix
							Sleep(300);
						} else
						if (running && bbs.exists(INV_S2_CHOICE_ENHANCE.similar(ACC_HIGH)) != null) {
							if (running) lfac("INV_S2_CHOICE_ENHANCE", INV_S2_CHOICE_ENHANCE, 1, false, ACC_HIGH); //TODO needs better image
							Sleep(700);
							if (running) goClick(300, 300); //bugfix
							Sleep(300);
						}
					}
					Sleep(3000);

					if (running) log("--- SortInv Mode...");
					
					if (running && bbs.exists(INV_PLUS.similar(ACC_095)) != null) {
						slide(INV_SLIDER, INV_PLUS);
						Sleep(700);
					}
					
					if (running) log("--- SortInv Mode....");
					//STAGE 3////////////////////////////////////////////////////////////////////////////////////////////////
					if (running && bbs.exists(INV_S3_OK.similar(ACC_HIGH)) != null) {
						if (running) lfac("INV_S3_OK", INV_S3_OK, 1, false, ACC_HIGH);
						Sleep(700);
					}
					
					if (running) log("--- SortInv Mode.....");
					//STAGE 4////////////////////////////////////////////////////////////////////////////////////////////////
					if (running && bbs.exists(INV_S4_OK.similar(ACC_HIGH)) != null) {
						if (running) lfac("INV_S4_OK", INV_S4_OK, 1, false, ACC_HIGH);
						Sleep(700);
					}
				
				} else
				if (bbs.exists(INV_MORE.similar(ACC_095-0.01f)) != null) {
					if (running) slidec(1400, 850, 1400, 350);
					if (running) Sleep(3000);
					if (bbs.exists(INV_RED_DOT.similar(ACC_095-0.01f)) == null) {
						done = true;
					}
				} else
				done = true;
				
			}
			
			if (running) lfac("EXIT", EXIT, DEFDELAY, false, ACCURACY);
			if (running) Sleep(3000);
			
			log("--- SortInv Mode Done..");
			done_in = true;
			jobDone();
			busy = false;

		});
		t.start();
		
	}
	
	//leave unconditionally
	public static void leaveParty() {
		if (running) goClick(BTN_PARTY_X, BTN_PARTY_Y); //party tab
		if (running) Sleep(2000);
		if (running && bbs.exists(DUNGEON_PARTY_MANAGE.similar(ACCURACY)) != null){
			if (running) lfac("DUNGEON_PARTY_MANAGE", DUNGEON_PARTY_MANAGE, 2, false, ACCURACY);
			if (running) Sleep(2000);
			if (running) lfac("DUNGEON_PARTY_DISBAND", DUNGEON_PARTY_DISBAND, 2, false, ACCURACY);
			if (running) Sleep2(5000);
			if (running) lfac("DUNGEON_PARTY_DISBAND_OK", DUNGEON_PARTY_DISBAND_OK, 5, false, ACCURACY);
			if (running) Sleep(2000);
		}
		if (running && bbs.exists(DUNGEON_PARTY_SEE.similar(ACCURACY)) != null) {
			if (running) lfac("DUNGEON_PARTY_SEE", DUNGEON_PARTY_SEE, 2, false, ACCURACY);
			if (running) Sleep(2000);
			if (running) lfac("DUNGEON_PARTY_LEAVE", DUNGEON_PARTY_LEAVE, 2, false, ACCURACY);
			if (running) Sleep2(5000);
			if (running) lfac("DUNGEON_PARTY_LEAVE_OK", DUNGEON_PARTY_LEAVE_OK, 5, false, ACCURACY);
			if (running) Sleep(2000);
		}
		if (running && bbs.exists(PARTY_20.similar(ACCURACY)) != null) {
			if (running) lfac("PARTY_20", PARTY_20, 2, false, ACCURACY);
			if (running) Sleep(2000);
			if (running) lfac("DUNGEON_PARTY_LEAVE", DUNGEON_PARTY_LEAVE, 2, false, ACCURACY);
			if (running) Sleep2(5000);
			if (running) lfac("DUNGEON_PARTY_LEAVE_OK", DUNGEON_PARTY_LEAVE_OK, 5, false, ACCURACY);
			if (running) Sleep(2000);
		}		
		
	}
	
	//leave if alone or 5%
	public static boolean exitBadParty() {
		boolean leftparty = false;
		if (running) goClick(BTN_PARTY_X, BTN_PARTY_Y-80); //party tab
		if (running) Sleep(2000);
		if (running && bbs.exists(DUNGEON_PARTY_MANAGE.similar(ACCURACY)) != null){
			if (running) lfac("DUNGEON_PARTY_MANAGE", DUNGEON_PARTY_MANAGE, 2, false, ACCURACY);
			if (running) Sleep(2000);
			if (running) lfac("DUNGEON_PARTY_DISBAND", DUNGEON_PARTY_DISBAND, 2, false, ACCURACY);
			if (running) Sleep2(5000);
			if (running) lfac("DUNGEON_PARTY_DISBAND_OK", DUNGEON_PARTY_DISBAND_OK, 5, false, ACCURACY);
			if (running) Sleep(2000);
			leftparty = true;
		}
		if (running && bbs.exists(DUNGEON_PARTY_5PERCENT.similar(ACCURACY)) != null) {
			if (running) lfac("DUNGEON_PARTY_SEE", DUNGEON_PARTY_SEE, 2, false, ACCURACY);
			if (running) Sleep(2000);
			if (running) lfac("DUNGEON_PARTY_LEAVE", DUNGEON_PARTY_LEAVE, 2, false, ACCURACY);
			if (running) Sleep2(5000);
			if (running) lfac("DUNGEON_PARTY_LEAVE_OK", DUNGEON_PARTY_LEAVE_OK, 5, false, ACCURACY);
			if (running) Sleep(2000);
			leftparty = true;
		}
		return leftparty;
	}
	
	static void startDungeon() {
		while (running && bbs.exists(BTN_DUNGEON.similar(ACCURACY)) == null) {
			if (running) exitMenues();
			if (running) goClick(BTN_MNU_X, BTN_MNU_Y); //menu
			if (running) Sleep(3000);
		}
		if (running) lfac("BTN_DUNGEON", BTN_DUNGEON, DEFDELAY, false, ACCURACY);
		if (running) Sleep(2000);
		
		if (running) lfac("BTN_DUNGEON_NORMAL", BTN_DUNGEON_NORMAL, DEFDELAY, false, ACCURACY);
		if (running) Sleep(2000);
		if (running) lfac("BTN_DUNGEON_ELITE", BTN_DUNGEON_ELITE, DEFDELAY, false, ACCURACY);
		if (running) Sleep(2000);
		if (running) slidec(400, 900, 400, 400);
		if (running) Sleep(3000);
	}
	
	static void sellStuff() {

		exitMenues();
		if (running) goClick(BTN_INV_X, BTN_INV_Y); //inventory
		if (running) Sleep(3000);
		
		if (running && bbs.exists(INV_SCREEN.similar(ACCURACY)) != null) {
		} else {
			exitMenues();
			if (running) goClick(BTN_INV_X, BTN_INV_Y); //inventory
			if (running) Sleep(3000);
		}
		
		if (running) lfac("INVSELL_BULK", INVSELL_BULK, DEFDELAY, false, ACCURACY);
		
		boolean selling = true;
		while (running && selling) {
			if (bbs.exists(INVSELL_SELL.similar(ACCURACY)) == null) {
				selling = false;
			} else {
				if (running) lfac("INVSELL_SELL", INVSELL_SELL, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
				if (running) lfac("INVSELL_SELL_CONF", INVSELL_SELL_CONF, DEFDELAY, false, ACCURACY);
				if (running) Sleep(4000);
				if (running) lfac("INVSELL_SELL_OK", INVSELL_SELL_OK, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
			}
		}
		
		lfac("EXIT", EXIT, DEFDELAY, false, ACCURACY);
		Sleep(2000);
		
	}
	
	public static void dungeonRun() {
		
		log("--- DungeonRun Mode..");
		start();
		done_dr = false;
		dungeonRunMode = true;
		
		Thread t = new Thread(() -> {
			
			if (dungeonInit) {
				
				startDungeon();

				int maxctr = 0;
				boolean notfound = true;
				while (running && maxctr < 8 && notfound) {
					if (bbs.exists(DUNGEON_RUN_CURRENT_BTN.similar(ACC_099)) != null) notfound = false; else {
						if (maxctr == 1 || maxctr == 3 || maxctr == 5) {
							if (running) slidec(400, 900, 400, 300);
						} 
						if (running) Sleep(3000);
					}
					maxctr++;
				}
				
				if (running) lfac("DUNGEON_RUN_CURRENT_BTN", DUNGEON_RUN_CURRENT_BTN, DEFDELAY, false, ACC_099);
				if (running) Sleep(2000);
				if (running) lfac("DUNGEON_ENTER", DUNGEON_ENTER, DEFDELAY, false, ACCURACY);
				if (running) Sleep(500);
				if (running && bbs.exists(DUNGEON_ENTER_CONFIRM.similar(ACCURACY)) != null) {
					if (running) lfac("DUNGEON_ENTER_CONFIRM", DUNGEON_ENTER_CONFIRM, DEFDELAY, false, ACCURACY);
					if (running) Sleep(500);
					if (running && bbs.exists(DUNGEON_ENTER_CONFIRM_CONFIRM.similar(ACCURACY)) != null) {
						if (running) lfac("DUNGEON_ENTER_CONFIRM_CONFIRM", DUNGEON_ENTER_CONFIRM_CONFIRM, 1, false, ACCURACY);
					}
				}
				if (running) Sleep2(DEF_STAGE_PAUSE);
			}

			if (running) goClick(BTN_PARTY_X, BTN_PARTY_Y-80); //party tab
			if (running) Sleep(2000);
			if (running && bbs.exists(DUNGEON_PARTY_SEARCH.similar(ACCURACY)) != null) {
				if (running) lfac("DUNGEON_PARTY_SEARCH", DUNGEON_PARTY_SEARCH, 2, false, ACCURACY);
				if (running) Sleep(2000);
				if (running) lfac("DUNGEON_PARTY_SEARCH_MANUAL", DUNGEON_PARTY_SEARCH_MANUAL, 2, false, ACCURACY);
				if (running) Sleep2(5000);
				if (running) lfac("DUNGEON_PARTY_JOIN", DUNGEON_PARTY_JOIN, 5, false, ACCURACY);
				if (running) Sleep(2000);
			}

			boolean dead = false;
			boolean leftparty = false;
			int ctrpos=0;
			int ctrparty=0;
			while (running) {
				if (dungeonInit || dead) {
					dungeonInit = false;
					dead = false;
					if (running) goClick(BTN_MAP_X, BTN_MAP_Y); //map
					if (running) Sleep(3000);
					if (running) goClick(DUNGEON_WP1_X, DUNGEON_WP1_Y); //dungeon wp1
					if (running) Sleep(DUNGEON_WP1_D*1000);
					if (running && DUNGEON_WP2_A) {
						if (running && bbs.exists(WORLD_MAP.similar(ACCURACY)) == null) {
							if (running) goClick(BTN_MAP_X, BTN_MAP_Y); //map
							if (running) Sleep(2000);
						}
						if (running) goClick(DUNGEON_WP2_X, DUNGEON_WP2_Y); //dungeon wp2
						if (running) Sleep(DUNGEON_WP2_D*1000);
					}
					if (running && DUNGEON_WP3_A) {
						if (running && bbs.exists(WORLD_MAP.similar(ACCURACY)) == null) {
							if (running) goClick(BTN_MAP_X, BTN_MAP_Y); //map
							if (running) Sleep(2000);
						}
						if (running) goClick(DUNGEON_WP3_X, DUNGEON_WP3_Y); //dungeon wp3
						if (running) Sleep(DUNGEON_WP3_D*1000);
					}
					if (running && DUNGEON_WP4_A) {
						if (running && bbs.exists(WORLD_MAP.similar(ACCURACY)) == null) {
							if (running) goClick(BTN_MAP_X, BTN_MAP_Y); //map
							if (running) Sleep(2000);
						}
						if (running) goClick(DUNGEON_WP4_X, DUNGEON_WP4_Y); //dungeon wp4
						if (running) Sleep(DUNGEON_WP4_D*1000);
					}
					if (running && DUNGEON_WP5_A) {
						if (running && bbs.exists(WORLD_MAP.similar(ACCURACY)) == null) {
							if (running) goClick(BTN_MAP_X, BTN_MAP_Y); //map
							if (running) Sleep(2000);
						}
						if (running) goClick(DUNGEON_WP5_X, DUNGEON_WP5_Y); //dungeon wp5
						if (running) Sleep(DUNGEON_WP5_D*1000);
					}
					
					if (running) Sleep(2000);
					if (running) lfac("DA_MAP_X", DA_MAP_X, DEFDELAY, false, ACCURACY);
					if (running) Sleep(2000);
					if (running) goClick(BTN_AUTO_X, BTN_AUTO_Y); //auto
					if (running) Sleep(2000);
				}
				
				if (running && (bbs.exists(MQ_SPOT_REVIVAL.similar(ACCURACY)) != null || bbs.exists(MQ_DEATH_X.similar(ACCURACY)) != null)) {
					if (running && bbs.exists(MQ_SPOT_REVIVAL.similar(ACCURACY)) != null) {
						if (running) lfac("MQ_SPOT_REVIVAL", MQ_SPOT_REVIVAL, DEFDELAY, false, ACCURACY);
						if (running) Sleep(4000);
						if (running) goClick(1840, 430); //X
						if (running) Sleep(2000);
					} else {
						if (running) lfac("MQ_DEATH_X", MQ_DEATH_X, DEFDELAY, false, ACCURACY);
						if (running) Sleep(2000);
					}
					dead = true;
				}
				
				if (running && ctrpos==0) {
					sellStuff();
				}
				
				if (running && ctrpos==20) {
					//log("checking daily quests...");
					dailyTimer = 19;
					if (running) checkOnDaily();
					
					//return to origin
					/*
					if (running) goClick(BTN_MAP_X, BTN_MAP_Y); //map
					if (running) Sleep(2000);
					if (running) goClick(DUNGEON_WPLAST_X, DUNGEON_WPLAST_Y); //go to final wp
					if (running) Sleep(2000);
					if (running) lfac("DA_MAP_X", DA_MAP_X, DEFDELAY, false, ACCURACY);
					if (running) Sleep(5000);
					if (running) goClick(BTN_AUTO_X, BTN_AUTO_Y); //auto
					if (running) Sleep(2000);
					*/
					
					ctrpos=0;
					//log("--- DungeonRun Mode..");
				}
				
				if (running && cb_DELAYED_RUN.isSelected() && itsTime()) {
					if (running) log("A new day to die - Running AUTO!");
					if (running) goClick(BTN_AUTO_X, BTN_AUTO_Y); //auto
					if (running) Sleep(4000);
					done_dr = true;
					dungeonRunMode = false;
					boolean inDungeon = true;
					while (running && inDungeon) {
						if (running) goClick(BTN_LEAVE_DUNGEON_X, BTN_LEAVE_DUNGEON_Y); //exit dungeon
						if (running) Sleep(500);
						if (bbs.exists(DA_DUNGEON_EXIT_CONF.similar(ACCURACY)) != null) {
							if (running) lfac("DA_EXIT_CONF", DA_DUNGEON_EXIT_CONF, 2, false, ACCURACY);
							if (running) Sleep(2000);
							if (bbs.exists(DA_DUNGEON_OK.similar(ACCURACY)) != null) {
								if (running) lfac("DA_DUNGEON_CANT_LEAVE_OK", DA_DUNGEON_OK, 3, false, ACCURACY);
								if (running) Sleep(2000);
							} else inDungeon = false;
						}
						if (running) Sleep(3000);
					}
					
					if (running) Sleep2(DEF_STAGE_PAUSE);
					jobDone();
					doItAll();
					busy = false;
					break;
				}
				//check if in party
				if (ctrparty==60) {
					leftparty = exitBadParty();

					if (running && (leftparty || bbs.exists(DUNGEON_PARTY_SEARCH.similar(ACCURACY)) != null)) {
						if (running) lfac("DUNGEON_PARTY_SEARCH", DUNGEON_PARTY_SEARCH, 2, false, ACCURACY);
						if (running) Sleep(2000);
						if (running) lfac("DUNGEON_PARTY_SEARCH_MANUAL", DUNGEON_PARTY_SEARCH_MANUAL, 2, false, ACCURACY);
						if (running) Sleep2(5000);
						if (running) lfac("DUNGEON_PARTY_JOIN", DUNGEON_PARTY_JOIN, 5, false, ACCURACY);
						if (running) Sleep(2000);
					}
					ctrparty=0;
				}
				
				
				//log("--- DungeonRun Mode Loop "+ctrpos);
				
				if (running) Sleep(2000);
				ctrpos++;
				ctrparty++;
			}

			done_dr = true;
			dungeonRunMode = false;
			jobDone();
			busy = false;
			
			
		});
		t.start();
		
	}
	
	static boolean itsTime() {
		boolean ok = false;
		String time = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
		if (time.equals(tf_DELAYED_RUN.getText())) ok = true;
		return ok;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static int startDaily(Pattern bp, String grade, String attempt) {
		int result = 0;
		try {
			if (running) log("--- Looking for DAILIES of type: "+grade);
			Pattern dq = bp.similar(ACC_HIGH);
			Iterator <Match> matches  = bbs.findAll(dq); 
			while (matches.hasNext()) {
				result++;
			    Match m = matches.next();
				int x = m.getX();
				int y = m.getY();
				if (running) log("--- Starting DAILY of type: "+grade);
				if (running) bbs.click(new Location(x, y+500*Float.parseFloat(setupScale.getText())/100));
				if (running) Sleep(2000);
			}
		} catch (FindFailed e) {
			e.printStackTrace();
		}
		if (running) Sleep(2000);
		if (running) log("Found "+result+" matches for Grade "+grade+" on try #"+attempt);
		return result;
	}
	
	
	public static void dailyStuff() {
		
		if (done_dailies) {
			if (bbs.exists(EXIT) != null) {
				if (running) lfac("EXIT", EXIT, 2, false, ACCURACY);
				if (running) Sleep(2000);
			}
			return;
		}
		
		while (running && bbs.exists(DAILY_CLAIM_REWARD.similar(ACCURACY)) != null) {
			if (running) lfac("DAILY_CLAIM_REWARD", DAILY_CLAIM_REWARD, DEFDELAY, false, ACCURACY);
			if (running) Sleep(2000);
		}
		
		if (running && bbs.exists(DAILY_COMPLETED.similar(0.99f)) != null) {
			done_dailies = true;
			if (running) log("> all daily quests done..");
			Sleep(2000);
		} else
		if (running && bbs.exists(DAILY_COMPLETE_W_COIN.similar(ACCURACY)) == null) {
			int found = 0;
			boolean exit = false;
			while (running && found == 0 && !exit) {
				if (running && bbs.exists(DAILY_R.similar(ACC_HIGH)) != null) {
					if (running) found = startDaily(DAILY_R, "R", "1");
					if (running && found == 0) Sleep(3000);
				}
				//recheck in case we missed something
				if (running && found == 0 && bbs.exists(DAILY_R.similar(ACC_HIGH)) != null) {
					if (running) found = startDaily(DAILY_R, "R", "2");
					if (running && found == 0) Sleep(2000);
				}
				
				if (running && found == 0 && bbs.exists(DAILY_S.similar(ACC_HIGH)) != null) {
					if (running) found = startDaily(DAILY_S, "S", "1");
					if (running && found == 0) Sleep(3000);
				}
				//recheck in case we missed something
				if (running && found == 0 && bbs.exists(DAILY_S.similar(ACC_HIGH)) != null) {
					if (running) found = startDaily(DAILY_S, "S", "2");
				}
				
				if (running && found == 0) {
					if (running) Sleep(2000);
					if (running && bbs.exists(DAILY_REFRESH.similar(ACCURACY)) != null) {
						if (running) lfac("DAILY_REFRESH", DAILY_REFRESH, DEFDELAY, false, ACCURACY);
						if (running) log("> Refreshing Dailies!");
					} else {
						exit = true;
						if (running) log("> Ran out of Daily Refreshes.");
						//done_dailies = true;
					}
				}
			}
		}
		
		if (running && bbs.exists(DAILY_REWARD1.similar(ACC_095)) != null) {
			if (running) lfac("DAILY_REWARD1", DAILY_REWARD1, DEFDELAY, false, ACC_095);
			if (running) Sleep(3000);
			if (running) log("> got daily reward 1..");
		} 
		if (running && bbs.exists(DAILY_REWARD2.similar(ACC_095)) != null) {
			if (running) lfac("DAILY_REWARD2", DAILY_REWARD2, DEFDELAY, false, ACC_095);
			if (running) Sleep(3000);
			if (running) log("> got daily reward 2..");
		} 
		
		if (running && dungeonRunMode) log("--- DungeonRun Mode..");
			
		if (bbs.exists(EXIT) != null) {
			if (running) lfac("EXIT", EXIT, 2, false, ACCURACY);
			if (running) Sleep(2000);
		}
	}
	
	static boolean buyPotions() {
		boolean ok = true;
		if (running) lfac("POTIONS_BUY", POTIONS_BUY, DEFDELAY, false, ACC_095);
		if (running) Sleep(2000);
		if (running) lfac("SUBQ_BUYSCROLL_BUY", SUBQ_BUYSCROLL_BUY, DEFDELAY, false, ACCURACY);
		if (running) Sleep2(5000);
		if (running && bbs.exists(POTIONS_NO_ADENA.similar(ACC_HIGH)) != null) {
			if (running) lfac("POTIONS_NO_ADENA_X", POTIONS_NO_ADENA_X, DEFDELAY, false, ACC_HIGH);
			if (running) Sleep(2000);
			ok = false;
		}
		return ok;
	}

	
	public static void checkOnDaily() {
		
		dailyTimer++;
		
		if (dailyTimer == 5 || dailyTimer == 10 || dailyTimer == 15 || dailyTimer == 20) {
			//if (running && (bbs.exists(POTIONS_0.similar(ACC_HIGH)) != null || bbs.exists(POTIONS_10.similar(ACC_ULTRA)) != null || bbs.exists(POTIONS_15.similar(ACC_ULTRA)) != null || bbs.exists(POTIONS_20.similar(ACC_ULTRA)) != null)) {
			if (running && bbs.exists(POTIONS_0.similar(ACC_HIGH)) != null) {
				if (running) goClick(BTN_SHOP_X, BTN_SHOP_Y); //shop
				if (running) Sleep2(5000);
				if (running) lfac("BTN_CONSUMABLES", BTN_CONSUMABLES, DEFDELAY, false, ACC_095);
				if (running) Sleep(2000);
				boolean ok;
				ok = buyPotions();
				if (ok) ok = buyPotions();
				if (ok) ok = buyPotions();
				if (running) lfac("EXIT", EXIT, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
			}
		}
		
		if (dailyTimer < 20) return;
		dailyTimer = 0;
		
		if (running && bbs.exists(TV_OK.similar(ACCURACY)) != null) { 
			if (running) lfac("TV_OK", TV_OK, 2, false, ACCURACY);
			if (running) Sleep(2000);
		}
		
		if (done_dailies) return;
		//if (!auto) return;
		
		if (!dungeonRunMode) {
			if (running && bbs.exists(EXIT) != null) {
				if (running) lfac("EXIT", EXIT, DEFDELAY, false, ACCURACY);
				if (running) Sleep(2000);
			}
			
			if (running) goClick(BTN_QUEST_X, BTN_QUEST_Y); //quests
		}
		
		if (running && (bbs.exists(DAILY_AVAILABLE.similar(ACCURACY)) != null || dungeonRunMode)) {
			if (running) lfac("QUESTLOG_START", QUESTLOG_START, 2, false, ACCURACY);
			if (running) Sleep(2000);
			if (running) lfac("DAILY_QUESTS", DAILY_QUESTS, 2, false, ACCURACY);
			if (running) Sleep(2000);
			dailyStuff();
		} else {
			if (running && bbs.exists(DAILY_DONE.similar(ACCURACY)) != null) {
				if (running) lfac("DAILY_DONE", DAILY_DONE, 2, false, ACCURACY);
				if (running) Sleep(2000);
				if (running && bbs.exists(PAGE_IS_DAILY_QUESTS) != null) {
					dailyStuff();
				} else {
					if (running && bbs.exists(EXIT) != null) {
						if (running) lfac("EXIT", EXIT, DEFDELAY, false, ACCURACY);
						if (running) Sleep(2000);
					}
				}
			}
		}
	}
	
	
	
	
}
