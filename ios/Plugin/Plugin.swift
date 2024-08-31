import Foundation
import Capacitor
import StoreKit

@objc(AppReview)
public class AppReview: CAPPlugin {
    @objc func request(_ call: CAPPluginCall) {
        SKStoreReviewController.requestReview()
        call.resolve()
    }
    
    @objc func openStore(_ call: CAPPluginCall) {
        call.resolve()
    }
}
