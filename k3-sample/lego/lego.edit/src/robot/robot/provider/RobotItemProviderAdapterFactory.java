/**
 */
package robot.robot.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import robot.robot.util.RobotAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class RobotItemProviderAdapterFactory extends RobotAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RobotItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link robot.robot.MoveCmd} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MoveCmdItemProvider moveCmdItemProvider;

	/**
	 * This creates an adapter for a {@link robot.robot.MoveCmd}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createMoveCmdAdapter() {
		if (moveCmdItemProvider == null) {
			moveCmdItemProvider = new MoveCmdItemProvider(this);
		}

		return moveCmdItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link robot.robot.ObstacleCmd} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObstacleCmdItemProvider obstacleCmdItemProvider;

	/**
	 * This creates an adapter for a {@link robot.robot.ObstacleCmd}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createObstacleCmdAdapter() {
		if (obstacleCmdItemProvider == null) {
			obstacleCmdItemProvider = new ObstacleCmdItemProvider(this);
		}

		return obstacleCmdItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link robot.robot.Bip} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BipItemProvider bipItemProvider;

	/**
	 * This creates an adapter for a {@link robot.robot.Bip}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBipAdapter() {
		if (bipItemProvider == null) {
			bipItemProvider = new BipItemProvider(this);
		}

		return bipItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link robot.robot.SetTurnAngleCmd} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SetTurnAngleCmdItemProvider setTurnAngleCmdItemProvider;

	/**
	 * This creates an adapter for a {@link robot.robot.SetTurnAngleCmd}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSetTurnAngleCmdAdapter() {
		if (setTurnAngleCmdItemProvider == null) {
			setTurnAngleCmdItemProvider = new SetTurnAngleCmdItemProvider(this);
		}

		return setTurnAngleCmdItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link robot.robot.HasTurnedCmd} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HasTurnedCmdItemProvider hasTurnedCmdItemProvider;

	/**
	 * This creates an adapter for a {@link robot.robot.HasTurnedCmd}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createHasTurnedCmdAdapter() {
		if (hasTurnedCmdItemProvider == null) {
			hasTurnedCmdItemProvider = new HasTurnedCmdItemProvider(this);
		}

		return hasTurnedCmdItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link robot.robot.TurnCmd} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TurnCmdItemProvider turnCmdItemProvider;

	/**
	 * This creates an adapter for a {@link robot.robot.TurnCmd}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTurnCmdAdapter() {
		if (turnCmdItemProvider == null) {
			turnCmdItemProvider = new TurnCmdItemProvider(this);
		}

		return turnCmdItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link robot.robot.StopEngineCmd} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StopEngineCmdItemProvider stopEngineCmdItemProvider;

	/**
	 * This creates an adapter for a {@link robot.robot.StopEngineCmd}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createStopEngineCmdAdapter() {
		if (stopEngineCmdItemProvider == null) {
			stopEngineCmdItemProvider = new StopEngineCmdItemProvider(this);
		}

		return stopEngineCmdItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link robot.robot.StopProgramCmd} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StopProgramCmdItemProvider stopProgramCmdItemProvider;

	/**
	 * This creates an adapter for a {@link robot.robot.StopProgramCmd}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createStopProgramCmdAdapter() {
		if (stopProgramCmdItemProvider == null) {
			stopProgramCmdItemProvider = new StopProgramCmdItemProvider(this);
		}

		return stopProgramCmdItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link robot.robot.PrintCmd} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PrintCmdItemProvider printCmdItemProvider;

	/**
	 * This creates an adapter for a {@link robot.robot.PrintCmd}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPrintCmdAdapter() {
		if (printCmdItemProvider == null) {
			printCmdItemProvider = new PrintCmdItemProvider(this);
		}

		return printCmdItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link robot.robot.ProgramUnit} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProgramUnitItemProvider programUnitItemProvider;

	/**
	 * This creates an adapter for a {@link robot.robot.ProgramUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createProgramUnitAdapter() {
		if (programUnitItemProvider == null) {
			programUnitItemProvider = new ProgramUnitItemProvider(this);
		}

		return programUnitItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (moveCmdItemProvider != null) moveCmdItemProvider.dispose();
		if (obstacleCmdItemProvider != null) obstacleCmdItemProvider.dispose();
		if (bipItemProvider != null) bipItemProvider.dispose();
		if (setTurnAngleCmdItemProvider != null) setTurnAngleCmdItemProvider.dispose();
		if (hasTurnedCmdItemProvider != null) hasTurnedCmdItemProvider.dispose();
		if (turnCmdItemProvider != null) turnCmdItemProvider.dispose();
		if (stopEngineCmdItemProvider != null) stopEngineCmdItemProvider.dispose();
		if (stopProgramCmdItemProvider != null) stopProgramCmdItemProvider.dispose();
		if (printCmdItemProvider != null) printCmdItemProvider.dispose();
		if (programUnitItemProvider != null) programUnitItemProvider.dispose();
	}

}