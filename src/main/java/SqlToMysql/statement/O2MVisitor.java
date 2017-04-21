package SqlToMysql.statement;

import SqlToMysql.util.CounterMap;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLHint;
import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.SQLStatement;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.function.Function;
// MySqlOutputVisitor
// OracleOutputVisitor

public class O2MVisitor extends OracleOutputVisitor {
	private static final Logger log = LogManager.getLogger();

	public static CounterMap<String> counter = new CounterMap<String>();
	public static CounterMap<String> okCounter = new CounterMap<String>();
	public O2MVisitor(Appendable appender) {
		super(appender, true);
	}

	public O2MVisitor(Appendable appender, boolean printPostSemi) {
		super(appender, printPostSemi);
	}


	private <T> boolean outContent(Function<T, Boolean> f, T x) {
		StringBuffer out = (StringBuffer) this.appender;
		int start = out.length();
		boolean result = f.apply(x);
		System.out.println(out.substring(start));
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
		counter.putOrIncrement("visit.OracleDeleteStatement");
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
	}

	public boolean visit(OracleSelectForUpdate x) {
		counter.putOrIncrement("visit.OracleSelectForUpdate");
		return super.visit(x);
	}

	public boolean visit(OracleSelectHierachicalQueryClause x) {
		counter.putOrIncrement("visit.OracleSelectHierachicalQueryClause");
		return super.visit(x);
	}

	public boolean visit(OracleSelectJoin x) {
		counter.putOrIncrement("visit.OracleSelectJoin");
		return super.visit(x);
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
		return super.visit(x);

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
		counter.putOrIncrement("visit.OracleUpdateStatement");
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
			return false;
		} else if (x.getExpr() instanceof SQLBinaryOpExpr) {
			SQLBinaryOpExpr expr = ((SQLBinaryOpExpr)x.getExpr());
			if (SQLBinaryOperator.Assignment.equals(expr.getOperator())) {
				return super.visit(x);
			}
		}
		okCounter.decrement("visit.OracleExprStatement");
		counter.putOrIncrement("visit.OracleExprStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleDatetimeExpr x) {
		counter.putOrIncrement("visit.OracleDatetimeExpr");
		return super.visit(x);
	}


	@Override
	public boolean visit(OracleSysdateExpr x) {
		counter.putOrIncrement("visit.OracleSysdateExpr");
		return super.visit(x);
	}


	@Override
	public boolean visit(com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleExceptionStatement.Item x) {
		counter.putOrIncrement("visit.com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleExceptionStatement.Item");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleExceptionStatement x) {
		counter.putOrIncrement("visit.OracleExceptionStatement");
		return super.visit(x);
	}

	@Override
	public boolean visit(OracleArgumentExpr x) {
		counter.putOrIncrement("visit.OracleArgumentExpr");
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
		return super.visit(x);
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
		return super.visit(x);
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
		counter.putOrIncrement("visit.SQLCharacterDataType");
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
					x.setMethodName("locate");
					x.getParameters().remove(2);
					return super.visit(x);
				}
			}
		} else if (lowerMethodName.equals("substr"))
			return super.visit(x);
		else if (lowerMethodName.equals("to_number")) {
			x.setMethodName("cast");
			boolean result = super.visit(x);
			StringBuffer output = (StringBuffer)getAppender();
			if (output.lastIndexOf(")") == output.length() - 1) {
				output.insert(output.length() - 1, " as signed ");
			} else
				log.error("to_number no parameter");
			return result;
		} else if (lowerMethodName.equals("length")) {
			x.setMethodName("char_length");
			return super.visit(x);
		} else if (lowerMethodName.equals("ltrim") || lowerMethodName.equals("rtrim") || lowerMethodName.equals("trim")) {
			if (x.getAttributes().size() == 1) {
				return super.visit(x);
			}
		}

		okCounter.decrement("visit.SQLMethodInvokeExpr");
		counter.putOrIncrement("visit.SQLMethodInvokeExpr");
		return super.visit(x);
	}
}
