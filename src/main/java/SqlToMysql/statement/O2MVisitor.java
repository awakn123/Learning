package SqlToMysql.statement;

import SqlToMysql.util.CounterMap;
import SqlToMysql.util.DataTypeConvert;
import com.alibaba.druid.sql.ast.*;
import com.alibaba.druid.sql.ast.expr.*;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.oracle.ast.OracleDataTypeIntervalDay;
import com.alibaba.druid.sql.dialect.oracle.ast.OracleDataTypeIntervalYear;
import com.alibaba.druid.sql.dialect.oracle.ast.OracleDataTypeTimestamp;
import com.alibaba.druid.sql.dialect.oracle.ast.OracleOrderBy;
import com.alibaba.druid.sql.dialect.oracle.ast.clause.*;
import com.alibaba.druid.sql.dialect.oracle.ast.clause.FlashbackQueryClause.AsOfFlashbackQueryClause;
import com.alibaba.druid.sql.dialect.oracle.ast.clause.FlashbackQueryClause.AsOfSnapshotClause;
import com.alibaba.druid.sql.dialect.oracle.ast.clause.FlashbackQueryClause.VersionsFlashbackQueryClause;
import com.alibaba.druid.sql.dialect.oracle.ast.clause.ModelClause.*;
import com.alibaba.druid.sql.dialect.oracle.ast.expr.*;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.*;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleAlterIndexStatement.Rebuild;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleAlterTableSplitPartition.NestedTablePartitionSpec;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleAlterTableSplitPartition.TableSpaceItem;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleAlterTableSplitPartition.UpdateIndexesClause;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleIfStatement.Else;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleIfStatement.ElseIf;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleMergeStatement.MergeInsertClause;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleMergeStatement.MergeUpdateClause;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleMultiInsertStatement.ConditionalInsertClause;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleMultiInsertStatement.ConditionalInsertClauseItem;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleMultiInsertStatement.InsertIntoClause;
import com.alibaba.druid.sql.dialect.oracle.visitor.OracleOutputVisitor;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
// MySqlOutputVisitor
// OracleOutputVisitor

public class O2MVisitor extends OracleOutputVisitor {
	private static final Logger log = LogManager.getLogger();

	public static CounterMap<String> counter = new CounterMap<String>();
	public static CounterMap<String> okCounter = new CounterMap<String>();
	public static Map<String, List<String>> errorMsgs = Maps.newHashMap();
	private static Set<String> sameFunctionNames = Sets.newHashSet("getpinyin", "lower", "concat", "fn_split", "table");
	private static Set<String> procedureNames = Sets.newHashSet("sysmaintenancelog_proc", "workflow_requestbase_insertnew", "workflow_requestlog_insert_new", "workflow_requestlog_op", "workflow_requestlogcurdate_new");

	public O2MVisitor(Appendable appender) {
		super(appender, true);
	}

	public O2MVisitor(Appendable appender, boolean printPostSemi) {
		super(appender, printPostSemi);
	}


	private <T> boolean outContent(Function<T, Boolean> f, T x, String key) {
		StringBuffer out = (StringBuffer) this.appender;
		int start = out.length();
		boolean result = f.apply(x);
		errorMsgs.putIfAbsent(key, Lists.newArrayList());
		errorMsgs.get(key). add(out.substring(start));
		return result;
	}

	public void postVisit(SQLObject x) {
		okCounter.putOrIncrement("postVisit.SQLObject");
		super.postVisit(x);
	}

	private void printHints(List<SQLHint> hints) {
		counter.putOrIncrement("printHints");
		if (hints.size() > 0) {
			print("/*+ ");
			printAndAccept(hints, ", ");
			print(" */");
		}
	}

	public boolean visit(SQLAllColumnExpr x) {
		okCounter.putOrIncrement("visit.SQLAllColumnExpr");
		return super.visit(x);
	}

	public boolean visit(OracleAnalytic x) {
		counter.putOrIncrement("visit.OracleAnalytic");
		return super.visit(x);
	}

	public boolean visit(OracleAnalyticWindowing x) {
		counter.putOrIncrement("visit.OracleAnalyticWindowing");
		return super.visit(x);
	}

	public boolean visit(OracleDateExpr x) {
		counter.putOrIncrement("visit.OracleDateExpr");
		return super.visit(x);
	}

	public boolean visit(OracleDbLinkExpr x) {
		counter.putOrIncrement("visit.OracleDbLinkExpr");
		return super.visit(x);
	}

	public boolean visit(OracleDeleteStatement x) {
		okCounter.putOrIncrement("visit.OracleDeleteStatement");
		return super.visit(x);
	}

	public boolean visit(OracleExtractExpr x) {
		counter.putOrIncrement("visit.OracleExtractExpr");
		return super.visit(x);
	}

	public boolean visit(OracleIntervalExpr x) {
		counter.putOrIncrement("visit.OracleIntervalExpr");
		return super.visit(x);
	}

	public boolean visit(OracleOrderBy x) {
		okCounter.putOrIncrement("visit.OracleOrderBy");
		return super.visit(x);
	}

	public boolean visit(OracleOuterExpr x) {
		counter.putOrIncrement("visit.OracleOuterExpr");
		return super.visit(x);
	}

	public boolean visit(OraclePLSQLCommitStatement astNode) {
		counter.putOrIncrement("visit.OraclePLSQLCommitStatement");
		return super.visit(astNode);
	}

	public boolean visit(SQLSelect x) {
		counter.putOrIncrement("visit.SQLSelect");
		return super.visit(x);
	}

	public boolean visit(OracleSelect x) {
		okCounter.putOrIncrement("visit.OracleSelect");
		return super.visit(x);
//		return outContent(a->super.visit(a), x,"visit.OracleSelect");
	}

	public boolean visit(OracleSelectForUpdate x) {
		counter.putOrIncrement("visit.OracleSelectForUpdate");
		return super.visit(x);
	}

	public boolean visit(OracleSelectHierachicalQueryClause x) {
		counter.putOrIncrement("Hierachical");
		return false;
	}

	public boolean visit(OracleSelectJoin x) {
		counter.putOrIncrement("visit.OracleSelectJoin");
		return outContent(a->super.visit(a), x, "OracleSelectJoin");
	}

	public boolean visit(OracleOrderByItem x) {
		okCounter.putOrIncrement("visit.OracleOrderByItem");
		return super.visit(x);
	}

	public boolean visit(OracleSelectPivot x) {
		counter.putOrIncrement("visit.OracleSelectPivot");
		return super.visit(x);

	}

	public boolean visit(OracleSelectPivot.Item x) {
		counter.putOrIncrement("visit.OracleSelectPivot.Item");
		return super.visit(x);

	}

	public boolean visit(SQLSelectQueryBlock select) {
		counter.putOrIncrement("visit.SQLSelectQueryBlock");
		return super.visit(select);

	}

	public boolean visit(OracleSelectQueryBlock x) {
		okCounter.putOrIncrement("visit.OracleSelectQueryBlock");

		print("SELECT ");

		if (x.getHints().size() > 0) {
			printAndAccept(x.getHints(), ", ");
			print(' ');
		}

		if (SQLSetQuantifier.ALL == x.getDistionOption()) {
			print("ALL ");
		} else if (SQLSetQuantifier.DISTINCT == x.getDistionOption()) {
			print("DISTINCT ");
		} else if (SQLSetQuantifier.UNIQUE == x.getDistionOption()) {
			print("UNIQUE ");
		}

		printSelectList(x.getSelectList());

		if (x.getInto() != null) {
			println();
			print("INTO ");
			x.getInto().accept(this);
		}

		println();
		if (x.getFrom() != null) {
			print("FROM ");
			if (x.getHierachicalQueryClause() == null || !(x.getFrom() instanceof SQLExprTableSource)) {
				x.getFrom().setParent(x);
				x.getFrom().accept(this);
			} else {
				SQLExprTableSource from = (SQLExprTableSource)x.getFrom();
				print("(");
				// hiera查询开始
				print("select tl.lv,t.* from (select @id idlist, @lv:=@lv+1 lv,");
				// 子查询开始
				print("(select @id:=group_concat(");
				SQLBinaryOpExpr connectBy = (SQLBinaryOpExpr)x.getHierachicalQueryClause().getConnectBy();
				connectBy.getLeft().accept(this);
				print(") from ");
				from.getExpr().accept(this);
				print(" where find_in_set(");
				connectBy.getRight().accept(this);
				print(",@id)) sub");
				// 子查询结束
				print(" from ");
				from.getExpr().accept(this);
				print(",(select @id:=");
				SQLBinaryOpExpr startExpr = (SQLBinaryOpExpr)x.getHierachicalQueryClause().getStartWith();
				startExpr.getRight().accept(this);
				print(",@lv:=0) vars where @id is not null) tl,");
				from.getExpr().accept(this);
				print(" t where find_in_set(t.id,tl.idlist)");
				// hiera查询结束
				print(")");
				print(' ');
				print(x.getFrom().getAlias() != null ? x.getFrom().getAlias() : "hiera");
			}
		}

		if (x.getWhere() != null) {
			println();
			print("WHERE ");
			x.getWhere().setParent(x);
			x.getWhere().accept(this);
		}

		if (x.getGroupBy() != null) {
			println();
			x.getGroupBy().accept(this);
		}

		if (x.getModelClause() != null) {
			println();
			x.getModelClause().accept(this);
		}
		return false;

	}

	public boolean visit(OracleSelectRestriction.CheckOption x) {
		counter.putOrIncrement("visit.OracleSelectRestriction.CheckOption");
		return super.visit(x);

	}

	public boolean visit(OracleSelectRestriction.ReadOnly x) {
		counter.putOrIncrement("visit.OracleSelectRestriction.ReadOnly");
		return super.visit(x);

	}

	public boolean visit(OracleSelectSubqueryTableSource x) {
		okCounter.putOrIncrement("visit.OracleSelectSubqueryTableSource");
		return super.visit(x);
	}

	public boolean visit(OracleSelectTableReference x) {
		okCounter.putOrIncrement("visit.OracleSelectTableReference");
		return super.visit(x);
//		return outContent(a -> super.visit(a), x, "visit.OracleSelectTableReference");
	}

	public boolean visit(OracleSelectUnPivot x) {
		counter.putOrIncrement("visit.OracleSelectUnPivot");
		return super.visit(x);

	}

	public boolean visit(OracleTimestampExpr x) {
		counter.putOrIncrement("visit.OracleTimestampExpr");
		return super.visit(x);

	}

	public boolean visit(OracleUpdateStatement x) {
		okCounter.putOrIncrement("visit.OracleUpdateStatement");
		return super.visit(x);

	}

	@Override
	public boolean visit(SampleClause x) {
		counter.putOrIncrement("visit.SampleClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(PartitionExtensionClause x) {
		counter.putOrIncrement("visit.PartitionExtensionClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(VersionsFlashbackQueryClause x) {
		counter.putOrIncrement("visit.VersionsFlashbackQueryClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(AsOfFlashbackQueryClause x) {
		counter.putOrIncrement("visit.AsOfFlashbackQueryClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(GroupingSetExpr x) {
		counter.putOrIncrement("visit.GroupingSetExpr");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleWithSubqueryEntry x) {
		counter.putOrIncrement("visit.OracleWithSubqueryEntry");
		return super.visit(x);
	}

	@Override
	public boolean visit(SearchClause x) {
		counter.putOrIncrement("visit.SearchClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(CycleClause x) {
		counter.putOrIncrement("visit.CycleClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleBinaryFloatExpr x) {
		counter.putOrIncrement("visit.OracleBinaryFloatExpr");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleBinaryDoubleExpr x) {
		counter.putOrIncrement("visit.OracleBinaryDoubleExpr");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleCursorExpr x) {
		counter.putOrIncrement("visit.OracleCursorExpr");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleIsSetExpr x) {
		counter.putOrIncrement("visit.OracleIsSetExpr");
		return super.visit(x);
	}

	@Override
	public boolean visit(ReturnRowsClause x) {
		counter.putOrIncrement("visit.ReturnRowsClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(ModelClause x) {
		counter.putOrIncrement("visit.ModelClause");
		return super.visit(x);
	}


	@Override
	public boolean visit(MainModelClause x) {
		counter.putOrIncrement("visit.MainModelClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(ModelColumnClause x) {
		counter.putOrIncrement("visit.ModelColumnClause");
		return super.visit(x);
	}
	@Override
	public boolean visit(QueryPartitionClause x) {
		counter.putOrIncrement("visit.QueryPartitionClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(ModelColumn x) {
		counter.putOrIncrement("visit.ModelColumn");
		return super.visit(x);
	}

	@Override
	public boolean visit(ModelRulesClause x) {
		counter.putOrIncrement("visit.ModelRulesClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(CellAssignmentItem x) {
		counter.putOrIncrement("visit.CellAssignmentItem");
		return super.visit(x);
	}
	@Override
	public boolean visit(CellAssignment x) {
		counter.putOrIncrement("visit.CellAssignment");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleMergeStatement x) {
		counter.putOrIncrement("visit.OracleMergeStatement");
		return super.visit(x);
	}


	@Override
	public boolean visit(MergeUpdateClause x) {
		counter.putOrIncrement("visit.MergeUpdateClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(MergeInsertClause x) {
		counter.putOrIncrement("visit.MergeInsertClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleErrorLoggingClause x) {
		counter.putOrIncrement("visit.OracleErrorLoggingClause");
		return super.visit(x);
	}


	@Override
	public boolean visit(OracleReturningClause x) {
		counter.putOrIncrement("visit.OracleReturningClause");
		return super.visit(x);
	}


	@Override
	public boolean visit(OracleInsertStatement x) {
		okCounter.putOrIncrement("visit.OracleInsertStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(InsertIntoClause x) {
		counter.putOrIncrement("visit.InsertIntoClause");
		return super.visit(x);
	}


	@Override
	public boolean visit(OracleMultiInsertStatement x) {
		counter.putOrIncrement("visit.OracleMultiInsertStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(ConditionalInsertClause x) {
		counter.putOrIncrement("visit.ConditionalInsertClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(ConditionalInsertClauseItem x) {
		counter.putOrIncrement("visit.ConditionalInsertClauseItem");
		return super.visit(x);
	}
	@Override
	public boolean visit(OracleBlockStatement x) {
		okCounter.putOrIncrement("visit.OracleBlockStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleLockTableStatement x) {
		counter.putOrIncrement("visit.OracleLockTableStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleAlterSessionStatement x) {
		counter.putOrIncrement("visit.OracleAlterSessionStatement");
		return super.visit(x);
	}


	@Override
	public boolean visit(OracleExprStatement x) {
		okCounter.putOrIncrement("visit.OracleExprStatement");
		if (x.getExpr() instanceof SQLIdentifierExpr) {
			x.getExpr().output((StringBuffer)getAppender());
			return false;
		} else if (x.getExpr() instanceof SQLMethodInvokeExpr) {
			String methodName = ((SQLMethodInvokeExpr) x.getExpr()).getMethodName();
			List<SQLExpr> exprs = ((SQLMethodInvokeExpr) x.getExpr()).getParameters();
			if (methodName.toLowerCase().equals("return")) {
				if (exprs.size() > 1) {
					log.error("return返回值有多个");
					return false;
				}
				((StringBuffer)getAppender()).append(methodName).append(" ");
				if (!exprs.isEmpty())
					exprs.get(0).output((StringBuffer)getAppender());
				return false;
			}
			return super.visit(x);
		} else if (x.getExpr() instanceof SQLBinaryOpExpr) {
			SQLBinaryOpExpr expr = ((SQLBinaryOpExpr)x.getExpr());
			if (SQLBinaryOperator.Assignment.equals(expr.getOperator())) {
				print("set ");
				return super.visit(x);
			}
		}
		okCounter.decrement("visit.OracleExprStatement");
		counter.putOrIncrement("visit.OracleExprStatement");
		return outContent(a->super.visit(a), x, "OracleExprStatement");
	}

	@Override
	public boolean visit(OracleDatetimeExpr x) {
		counter.putOrIncrement("visit.OracleDatetimeExpr");
		return super.visit(x);
	}


	@Override
	public boolean visit(OracleSysdateExpr x) {
		okCounter.putOrIncrement("visit.OracleSysdateExpr");
		print("NOW()");
		if (x.getOption() != null) {
			print("@");
			print(x.getOption());
		}
		return false;
	}


	@Override
	public boolean visit(com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleExceptionStatement.Item x) {
		counter.putOrIncrement("Exception When");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleExceptionStatement x) {
		counter.putOrIncrement("Exception");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleArgumentExpr x) {
		counter.putOrIncrement("=>expression");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleSetTransactionStatement x) {
		counter.putOrIncrement("visit.OracleSetTransactionStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleExplainStatement x) {
		counter.putOrIncrement("visit.OracleExplainStatement");
		return super.visit(x);
	}


	@Override
	public boolean visit(OracleAlterProcedureStatement x) {
		counter.putOrIncrement("visit.OracleAlterProcedureStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleAlterTableDropPartition x) {
		counter.putOrIncrement("visit.OracleAlterTableDropPartition");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleAlterTableStatement x) {
		counter.putOrIncrement("visit.OracleAlterTableStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleAlterTableTruncatePartition x) {
		counter.putOrIncrement("visit.OracleAlterTableTruncatePartition");
		return super.visit(x);
	}

	@Override
	public boolean visit(TableSpaceItem x) {
		counter.putOrIncrement("visit.TableSpaceItem");
		return super.visit(x);
	}

	@Override
	public boolean visit(UpdateIndexesClause x) {
		counter.putOrIncrement("visit.UpdateIndexesClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleAlterTableSplitPartition x) {
		counter.putOrIncrement("visit.OracleAlterTableSplitPartition");
		return super.visit(x);
	}

	@Override
	public boolean visit(NestedTablePartitionSpec x) {
		counter.putOrIncrement("visit.NestedTablePartitionSpec");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleAlterTableModify x) {
		counter.putOrIncrement("visit.OracleAlterTableModify");
		return super.visit(x);
	}


	@Override
	public boolean visit(OracleCreateIndexStatement x) {
		counter.putOrIncrement("visit.OracleCreateIndexStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleAlterIndexStatement x) {
		counter.putOrIncrement("visit.OracleAlterIndexStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(Rebuild x) {
		counter.putOrIncrement("visit.Rebuild");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleForStatement x) {
		counter.putOrIncrement("visit.OracleForStatement");
		println("DECLARE DONE TINYINT DEFAULT 0;");
		print("DECLARE ");
		x.getIndex().accept(this);
		print(" CURSOR FOR ");
		x.getRange().accept(this);
		print(";");
		println();
		println("DECLARE continue handler for sqlstate '02000' set DONE=1;");
		print("OPEN ");
		x.getIndex().accept(this);
		println(";");
		print("FETCH ");
		x.getIndex().accept(this);
		println(" INTO ROW;");
		println("WHILE DONE!=1 DO");
		incrementIndent();
		println();

		for (int i = 0, size = x.getStatements().size(); i < size; ++i) {
			SQLStatement item = x.getStatements().get(i);
			item.setParent(x);
			item.accept(this);
			if (i != size - 1) {
				println(";");
			}
		}

		decrementIndent();
		println(";");
		print("FETCH ");
		x.getIndex().accept(this);
		println(" INTO ROW;");
		println("END WHILE;");
		print("CLOSE ");
		x.getIndex().accept(this);
		println(";");
		return false;
	}

	@Override
	public boolean visit(Else x) {
		okCounter.putOrIncrement("visit.Else");
		return super.visit(x);
	}

	@Override
	public boolean visit(ElseIf x) {
		okCounter.putOrIncrement("visit.ElseIf");
		print("ELSEIF ");
		x.getCondition().accept(this);
		print(" THEN");
		incrementIndent();
		println();

		for (int i = 0, size = x.getStatements().size(); i < size; ++i) {
			if (i != 0) {
				println();
			}
			SQLStatement item = x.getStatements().get(i);
			item.setParent(x);
			item.accept(this);
		}
		print(";");

		decrementIndent();
		return false;
	}
	@Override
	public boolean visit(OracleIfStatement x) {
		okCounter.putOrIncrement("visit.OracleIfStatement");
		super.visit(x);
		print(";");
		return false;
	}

	@Override
	public boolean visit(OracleRangeExpr x) {
		counter.putOrIncrement("visit.OracleRangeExpr");
		return super.visit(x);
	}

	protected void visitColumnDefault(SQLColumnDefinition x) {
		counter.putOrIncrement("visitColumnDefault.SQLColumnDefinition");
		super.visitColumnDefault(x);
	}

	@Override
	public boolean visit(OracleAlterTableAddConstaint x) {
		counter.putOrIncrement("visit.OracleAlterTableAddConstaint");
		return super.visit(x);
	}

	@Override
	public boolean visit(OraclePrimaryKey x) {
		counter.putOrIncrement("visit.OraclePrimaryKey");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleCreateTableStatement x) {
		counter.putOrIncrement("visit.OracleCreateTableStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleAlterTableRenameTo x) {
		counter.putOrIncrement("visit.OracleAlterTableRenameTo");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleStorageClause x) {
		counter.putOrIncrement("visit.OracleStorageClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleGotoStatement x) {
		counter.putOrIncrement("visit.OracleGotoStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleLabelStatement x) {
		counter.putOrIncrement("visit.OracleLabelStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleParameter x) {
		counter.putOrIncrement("visit.OracleParameter");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleCommitStatement x) {
		counter.putOrIncrement("visit.OracleCommitStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleAlterTriggerStatement x) {
		counter.putOrIncrement("visit.OracleAlterTriggerStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleAlterSynonymStatement x) {
		counter.putOrIncrement("visit.OracleAlterSynonymStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(AsOfSnapshotClause x) {
		counter.putOrIncrement("visit.AsOfSnapshotClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleAlterViewStatement x) {
		counter.putOrIncrement("visit.OracleAlterViewStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleAlterTableMoveTablespace x) {
		counter.putOrIncrement("visit.OracleAlterTableMoveTablespace");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleSizeExpr x) {
		counter.putOrIncrement("visit.OracleSizeExpr");
		return super.visit(x);
	}


	@Override
	public boolean visit(OracleFileSpecification x) {
		counter.putOrIncrement("visit.OracleFileSpecification");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleAlterTablespaceAddDataFile x) {
		counter.putOrIncrement("visit.OracleAlterTablespaceAddDataFile");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleAlterTablespaceStatement x) {
		counter.putOrIncrement("visit.OracleAlterTablespaceStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(SQLTruncateStatement x) {
		counter.putOrIncrement("visit.SQLTruncateStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleCreateSequenceStatement x) {
		counter.putOrIncrement("visit.OracleCreateSequenceStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleRangeValuesClause x) {
		counter.putOrIncrement("visit.OracleRangeValuesClause");
		return super.visit(x);
	}
	@Override
	public boolean visit(OraclePartitionByRangeClause x) {
		counter.putOrIncrement("visit.OraclePartitionByRangeClause");
		return super.visit(x);
	}


	@Override
	public boolean visit(OracleLoopStatement x) {
		okCounter.putOrIncrement("visit.OracleLoopStatement");
		print("label1: ");
		boolean result = super.visit(x);
		println(" label1");
		return result;
	}

	@Override
	public boolean visit(OracleExitStatement x) {
		okCounter.putOrIncrement("visit.OracleExitStatement");
		print("LEAVE");
		if (x.getWhen() != null) {
			print(" WHEN ");
			x.getWhen().accept(this);
		}
		return false;
	}


	@Override
	public boolean visit(OracleFetchStatement x) {
		counter.putOrIncrement("visit.OracleFetchStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleSavePointStatement x) {
		counter.putOrIncrement("visit.OracleSavePointStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleCreateProcedureStatement x) {
		counter.putOrIncrement("visit.OracleCreateProcedureStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleCreateDatabaseDbLinkStatement x) {
		counter.putOrIncrement("visit.OracleCreateDatabaseDbLinkStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleDropDbLinkStatement x) {
		counter.putOrIncrement("visit.OracleDropDbLinkStatement");
		return super.visit(x);
	}

	public boolean visit(SQLCharacterDataType x) {
		x.setName(DataTypeConvert.oracleToMysql(x.getName()));
		okCounter.putOrIncrement("visit.SQLCharacterDataType");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleDataTypeTimestamp x) {
		counter.putOrIncrement("visit.OracleDataTypeTimestamp");
		return super.visit(x);
	}


	@Override
	public boolean visit(OracleDataTypeIntervalYear x) {
		counter.putOrIncrement("visit.OracleDataTypeIntervalYear");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleDataTypeIntervalDay x) {
		counter.putOrIncrement("visit.OracleDataTypeIntervalDay");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleUsingIndexClause x) {
		counter.putOrIncrement("visit.OracleUsingIndexClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleLobStorageClause x) {
		counter.putOrIncrement("visit.OracleLobStorageClause");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleUnique x) {
		counter.putOrIncrement("visit.OracleUnique");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleForeignKey x) {
		counter.putOrIncrement("visit.OracleForeignKey");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleCheck x) {
		counter.putOrIncrement("visit.OracleCheck");
		return super.visit(x);
	}

	@Override
	public boolean visit(SQLMethodInvokeExpr x) {
		okCounter.putOrIncrement("visit.SQLMethodInvokeExpr");
		String lowerMethodName = x.getMethodName().toLowerCase();
		if (lowerMethodName.equals("instr")) {
			if (x.getParameters().size() == 2)
				return super.visit(x);
			else if (x.getParameters().size() > 2) {
				if (x.getParameters().get(2).toString().equals("1")) {
					print("CHAR_LENGTH(SUBSTRING_INDEX(");
					x.getParameters().remove(2);
					for (int i = 0, size = x.getParameters().size(); i < size; ++i) {
						if (i != 0) {
							print(", ");
						}
						x.getParameters().get(i).accept(this);
					}
					print(")) + 1");
					return false;
				}
			}
		} else if (lowerMethodName.equals("substr"))
			return super.visit(x);
		else if (lowerMethodName.equals("to_number")) {
			return doCastFunction(x, "signed");
		} else if (lowerMethodName.equals("length")) {
			x.setMethodName("char_length");
			return super.visit(x);
		} else if (lowerMethodName.equals("ltrim") || lowerMethodName.equals("rtrim") || lowerMethodName.equals("trim")) {
			if (x.getAttributes().size() == 1) {
				return super.visit(x);
			}
		} else if (lowerMethodName.equals("to_char")) {
			if (x.getParameters().size() > 1) {
				x.setMethodName("date_format");
				return super.visit(x);
			} else if (x.getParameters().size() == 1) {
				return doCastFunction(x, "CHAR");
			}
		} else if (lowerMethodName.equals("nvl")) {
			x.setMethodName("ifnull");
			return super.visit(x);
		} else if (sameFunctionNames.contains(lowerMethodName))
			return super.visit(x);
		else if (procedureNames.contains(lowerMethodName)) {
			print("CALL ");
			return super.visit(x);
		} else if (lowerMethodName.equals("to_date")) {
			x.setMethodName("STR_TO_DATE");
			return super.visit(x);
		}

		okCounter.decrement("visit.SQLMethodInvokeExpr");
		counter.putOrIncrement("visit.SQLMethodInvokeExpr");
		return outContent(a->super.visit(a), x, "SQLMethodInvokeExpr");
	}

	private boolean doCastFunction(SQLMethodInvokeExpr x, String mark) {
		x.setMethodName("cast");
		boolean result = super.visit(x);
		StringBuffer output = (StringBuffer)getAppender();
		if (output.lastIndexOf(")") == output.length() - 1) {
			output.insert(output.length() - 1, " as " + mark);
		} else
			log.error("cast no parameter");
		return result;
	}

	public boolean visit(SQLVariantRefExpr x) {
		List<Object> parameters = this.getParameters();
		int index = x.getIndex();

		if (parameters == null || parameters.isEmpty() || index >= parameters.size()) {
			String lowerName = x.getName().toLowerCase();
			if (lowerName.equals(":new"))
				print("new");
			else if (lowerName.equals(":old"))
				print("old");
			else if (lowerName.equals("sysdate"))
				print("now()");
			else
				print(x.getName());
			return false;
		}

		Object param = parameters.get(index);
		printParameter(param);
		return false;
	}

	public boolean visit(SQLCharExpr x) {
		if ((x.getText() == null) || (x.getText().length() == 0)) {
			print("NULL");
		} else {
			print("'");
			String lowerValue = x.getText().toLowerCase();
			if (lowerValue.equals("yyyy-mm-dd"))
				print("%Y-%m-%d");
			else if (lowerValue.equals("hh24:mi:ss"))
				print("%H:%i:%S");
			else if (lowerValue.equalsIgnoreCase("yyyy-mm-dd hh24:mi:ss"))
				print("%Y-%m-%d %H:%i:%S");
			else
				print(x.getText().replaceAll("'", "''"));
			print("'");
		}

		return false;
	}
}
